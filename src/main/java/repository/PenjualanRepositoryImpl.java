package repository;

import entity.Penjualan;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PenjualanRepositoryImpl implements PenjualanRepository{

    private DataSource dataSource;

    public PenjualanRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Penjualan[] getAll() {
        String sql = "select * from penjualan";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ) {

            List<Penjualan> list = new ArrayList<>();
            while (resultSet.next()){
                Penjualan penjualan = new Penjualan();

                penjualan.setId(resultSet.getString("id"));
                penjualan.setNoPenjualan(resultSet.getString("nopenjualan"));
                penjualan.setTglPenjualan(resultSet.getDate("tglpenjualan"));
                penjualan.setKdPelanggan(resultSet.getString("kdpelanggan"));

                list.add(penjualan);
            }

            return list.toArray(new Penjualan[]{});

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new Penjualan[0];
        }
    }

    @Override
    public Penjualan[] getById(String id) {
        String sql = "select * from penjualan where id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Penjualan penjualan = new Penjualan();
                    List<Penjualan> list = new ArrayList<>();
                    penjualan.setId(resultSet.getString("id"));
                    penjualan.setNoPenjualan(resultSet.getString("nopenjualan"));
                    penjualan.setTglPenjualan(resultSet.getDate("tglpenjualan"));
                    penjualan.setKdPelanggan(resultSet.getString("kdpelanggan"));

                    list.add(penjualan);
                    return list.toArray(new Penjualan[]{});
                }else{
                    return new Penjualan[]{};
                }
            } catch (SQLException exception) {
                //throw new RuntimeException(exception);
                System.out.println("Pesan eror : "+exception);
                return new Penjualan[0];
            }

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new Penjualan[0];
        }
    }

    @Override
    public int add(Penjualan penjualan) {
        String sql = "INSERT INTO penjualan(id,nopenjualan,tglpenjualan, kdpelanggan) VALUES (?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, penjualan.getId());
            statement.setString(2, penjualan.getNoPenjualan());
            statement.setDate(3, (Date) penjualan.getTglPenjualan());
            statement.setString(4, penjualan.getKdPelanggan());

            return statement.executeUpdate();

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

    @Override
    public int removeById(String id) {
        String sql = "DELETE FROM penjualan WHERE id = ?";

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
        String sql = "DELETE FROM penjualan";

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
    public int updateById(Penjualan penjualan) {
        String sql = "update penjualan " +
                "set nopenjualan = ? ,"+
                "tgl_penjualan = ? ,"+
                "kdpelanggan = ? "+
                "WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, penjualan.getNoPenjualan());
            statement.setDate(2, (Date) penjualan.getTglPenjualan());
            statement.setString(3, penjualan.getKdPelanggan());
            statement.setString(4, penjualan.getId());

            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }
}
