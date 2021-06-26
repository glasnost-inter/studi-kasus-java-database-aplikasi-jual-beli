package repository;

import entity.Pembelian;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PembelianRepositoryImpl implements PembelianRepository{

    private DataSource dataSource;

    public PembelianRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Pembelian[] getAll() {
        String sql = "select * from pembelian";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ) {

            List<Pembelian> list = new ArrayList<>();
            while (resultSet.next()){
                Pembelian pembelian = new Pembelian();

                pembelian.setId(resultSet.getString("id"));
                pembelian.setNoPembelian(resultSet.getString("nopembelian"));
                pembelian.setTglPembelian(resultSet.getDate("tglpembelian"));
                pembelian.setKdPemasok(resultSet.getString("kdpemasok"));

                list.add(pembelian);
            }

            return list.toArray(new Pembelian[]{});

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new Pembelian[0];
        }
    }

    @Override
    public Pembelian[] getById(String id) {
        String sql = "select * from pembelian where id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Pembelian pembelian = new Pembelian();
                    List<Pembelian> list = new ArrayList<>();
                    pembelian.setId(resultSet.getString("id"));
                    pembelian.setNoPembelian(resultSet.getString("nopembelian"));
                    pembelian.setTglPembelian(resultSet.getDate("tglpembelian"));
                    pembelian.setKdPemasok(resultSet.getString("kdpemasok"));

                    list.add(pembelian);
                    return list.toArray(new Pembelian[]{});
                }else{
                    return new Pembelian[]{};
                }
            } catch (SQLException exception) {
                //throw new RuntimeException(exception);
                System.out.println("Pesan eror : "+exception);
                return new Pembelian[0];
            }

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new Pembelian[0];
        }
    }

    @Override
    public int add(Pembelian pembelian) {
        String sql = "INSERT INTO pembelian(id,nopembelian,tglpembelian,kdpemasok) VALUES (?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, pembelian.getId());
            statement.setString(2, pembelian.getNoPembelian());
            statement.setDate(3, (Date) pembelian.getTglPembelian());
            statement.setString(4, pembelian.getKdPemasok());

            return statement.executeUpdate();

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

    @Override
    public int removeById(String id) {
        String sql = "DELETE FROM pembelian WHERE id = ?";

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
        String sql = "DELETE FROM pembelian";

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
    public int updateById(Pembelian pembelian) {
        String sql = "update pembelian " +
                "set nopembelian = ? ,"+
                "tglpembelian = ? ,"+
                "kdpemasok = ? "+
                "WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, pembelian.getNoPembelian());
            statement.setDate(2, (Date) pembelian.getTglPembelian());
            statement.setString(3, pembelian.getKdPemasok());
            statement.setString(4, pembelian.getId());

            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }
}
