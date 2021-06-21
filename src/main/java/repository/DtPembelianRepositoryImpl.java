package repository;

import entity.DtPembelian;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DtPembelianRepositoryImpl implements DtPembelianRepository {
    private DataSource dataSource;

    public DtPembelianRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DtPembelian[] getAll() {
        String sql = "select * from dtpembelian";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ) {

            List<DtPembelian> list = new ArrayList<>();
            while (resultSet.next()){
                DtPembelian dtPembelian = new DtPembelian();

                dtPembelian.setId(resultSet.getString("id"));
                dtPembelian.setIdPembelian(resultSet.getString("idpembelian"));
                dtPembelian.setIdBrg(resultSet.getString("idbrg"));
                dtPembelian.setJumlah(resultSet.getInt("jumlah"));
                dtPembelian.setHarga(resultSet.getInt("harga"));

                list.add(dtPembelian);
            }

            return list.toArray(new DtPembelian[]{});

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new DtPembelian[0];
        }
    }

    @Override
    public DtPembelian[] getById(String id) {
        String sql = "select * from dtPembelian where id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    DtPembelian dtPembelian = new DtPembelian();
                    List<DtPembelian> list = new ArrayList<>();
                    dtPembelian.setId(resultSet.getString("id"));
                    dtPembelian.setIdPembelian(resultSet.getString("idpembelian"));
                    dtPembelian.setIdBrg(resultSet.getString("idbrg"));
                    dtPembelian.setJumlah(resultSet.getInt("jumlah"));
                    dtPembelian.setHarga(resultSet.getInt("harga"));

                    list.add(dtPembelian);
                    return list.toArray(new DtPembelian[]{});
                }else{
                    return new DtPembelian[]{};
                }
            } catch (SQLException exception) {
                //throw new RuntimeException(exception);
                System.out.println("Pesan eror : "+exception);
                return new DtPembelian[0];
            }

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new DtPembelian[0];
        }
    }

    @Override
    public int add(DtPembelian dtPembelian) {
        String sql = "INSERT INTO dtPembelian(id,idpembelian,idbrg,jumlah,harga) VALUES (?,?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, dtPembelian.getId());
            statement.setString(2, dtPembelian.getIdPembelian());
            statement.setString(3, dtPembelian.getIdBrg());
            statement.setInt(4, dtPembelian.getJumlah());
            statement.setInt(5, dtPembelian.getHarga());

            return statement.executeUpdate();

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

    @Override
    public int removeById(String id) {
        String sql = "DELETE FROM dtPembelian WHERE id = ?";

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
    public int updateById(DtPembelian dtPembelian) {
        String sql = "update dtPembelian " +
                "set idpembelian = ? ,"+
                "idbrg = ? ,"+
                "jumlah = ? ,"+
                "harga = ? "+
                "WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, dtPembelian.getIdPembelian());
            statement.setString(2, dtPembelian.getIdBrg());
            statement.setInt(3, dtPembelian.getJumlah());
            statement.setInt(4, dtPembelian.getHarga());
            statement.setString(5, dtPembelian.getId());

            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }
}
