package repository;

import entity.DtPenjualan;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DtPenjualanRepositoryImpl implements DtPenjualanRepository{

    private DataSource dataSource;

    public DtPenjualanRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DtPenjualan[] getAll() {
        String sql = "select * from dtpenjualan";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ) {

            List<DtPenjualan> list = new ArrayList<>();
            while (resultSet.next()){
                DtPenjualan dtPenjualan = new DtPenjualan();

                dtPenjualan.setId(resultSet.getString("id"));
                dtPenjualan.setIdPenjualan(resultSet.getString("idpenjualan"));
                dtPenjualan.setIdBrg(resultSet.getString("idbrg"));
                dtPenjualan.setJumlah(resultSet.getInt("jumlah"));
                dtPenjualan.setHarga(resultSet.getInt("harga"));

                list.add(dtPenjualan);
            }

            return list.toArray(new DtPenjualan[]{});

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new DtPenjualan[0];
        }
    }

    @Override
    public DtPenjualan[] getById(String id) {
        String sql = "select * from dtPenjualan where id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    DtPenjualan dtPenjualan = new DtPenjualan();
                    List<DtPenjualan> list = new ArrayList<>();
                    dtPenjualan.setId(resultSet.getString("id"));
                    dtPenjualan.setIdPenjualan(resultSet.getString("idpenjualan"));
                    dtPenjualan.setIdBrg(resultSet.getString("idbrg"));
                    dtPenjualan.setJumlah(resultSet.getInt("jumlah"));
                    dtPenjualan.setHarga(resultSet.getInt("harga"));

                    list.add(dtPenjualan);
                    return list.toArray(new DtPenjualan[]{});
                }else{
                    return new DtPenjualan[]{};
                }
            } catch (SQLException exception) {
                //throw new RuntimeException(exception);
                System.out.println("Pesan eror : "+exception);
                return new DtPenjualan[0];
            }

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new DtPenjualan[0];
        }
    }

    @Override
    public int add(DtPenjualan dtPenjualan) {
        String sql = "INSERT INTO dtPenjualan(id,idpenjualan,idbrg,jumlah,harga) VALUES (?,?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, dtPenjualan.getId());
            statement.setString(2, dtPenjualan.getIdPenjualan());
            statement.setString(3, dtPenjualan.getIdBrg());
            statement.setInt(4, dtPenjualan.getJumlah());
            statement.setInt(5, dtPenjualan.getHarga());

            return statement.executeUpdate();

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

    @Override
    public int removeById(String id) {
        String sql = "DELETE FROM dtPenjualan WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, id);
            return statement.executeUpdate();
        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

    @Override
    public int removeAll() {
        String sql = "DELETE FROM dtPenjualan";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            return statement.executeUpdate();
        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

    @Override
    public int updateById(DtPenjualan dtPenjualan) {
        String sql = "update dtPenjualan " +
                "set idpenjualan = ? ,"+
                "idbrg = ? ,"+
                "jumlah = ? ,"+
                "harga = ? "+
                "WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, dtPenjualan.getIdPenjualan());
            statement.setString(2, dtPenjualan.getIdBrg());
            statement.setInt(3, dtPenjualan.getJumlah());
            statement.setInt(4, dtPenjualan.getHarga());
            statement.setString(5, dtPenjualan.getId());

            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }
}
