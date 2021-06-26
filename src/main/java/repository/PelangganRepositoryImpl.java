package repository;

import entity.Pelanggan;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganRepositoryImpl implements PelangganRepository{

    private DataSource dataSource;

    public PelangganRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Pelanggan[] getAll() {

        String sql = "select * from Pelanggan";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ) {

            List<Pelanggan> list = new ArrayList<>();
            while (resultSet.next()){
                Pelanggan pelanggan = new Pelanggan();
                pelanggan.setId(resultSet.getString("id"));
                pelanggan.setKdPelanggan(resultSet.getString("kdpelanggan"));
                pelanggan.setNmPelanggan(resultSet.getString("nmpelanggan"));
                pelanggan.setAlmPelanggan1(resultSet.getString("almpelanggan1"));
                pelanggan.setAlmPelanggan2(resultSet.getString("almpelanggan2"));
                pelanggan.setEmailPelanggan(resultSet.getString("emailpelanggan"));

                list.add(pelanggan);
            }

            return list.toArray(new Pelanggan[]{});

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new Pelanggan[0];
        }

    }

    @Override
    public Pelanggan[] getById(String id) {
        String sql = "select * from Pelanggan where id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Pelanggan pelanggan = new Pelanggan();
                    List<Pelanggan> list = new ArrayList<>();
                    pelanggan.setId(resultSet.getString("id"));
                    pelanggan.setKdPelanggan(resultSet.getString("kdpelanggan"));
                    pelanggan.setNmPelanggan(resultSet.getString("nmpelanggan"));
                    pelanggan.setAlmPelanggan1(resultSet.getString("almpelanggan1"));
                    pelanggan.setAlmPelanggan2(resultSet.getString("almpelanggan2"));
                    pelanggan.setEmailPelanggan(resultSet.getString("emailpelanggan"));

                    list.add(pelanggan);
                    return list.toArray(new Pelanggan[]{});
                }else{
                    return new Pelanggan[]{};
                }
            } catch (SQLException exception) {
                //throw new RuntimeException(exception);
                System.out.println("Pesan eror : "+exception);
                return new Pelanggan[0];
            }

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new Pelanggan[0];
        }
    }

    @Override
    public int add(Pelanggan pelanggan) {
        String sql = "INSERT INTO Pelanggan(id,kdpelanggan,nmpelanggan,almpelanggan1,almpelanggan2,emailpelanggan) VALUES (?,?,?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, pelanggan.getId());
            statement.setString(2, pelanggan.getKdPelanggan());
            statement.setString(3, pelanggan.getNmPelanggan());
            statement.setString(4, pelanggan.getAlmPelanggan1());
            statement.setString(5, pelanggan.getAlmPelanggan2());
            statement.setString(6, pelanggan.getEmailPelanggan());

            return statement.executeUpdate();

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

    @Override
    public int removeById(String id) {
        String sql = "DELETE FROM Pelanggan WHERE id = ?";

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
        String sql = "DELETE FROM Pelanggan";

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
    public int updateById(Pelanggan pelanggan) {
        String sql = "update Pelanggan " +
                "set kdpelanggan = ? ,"+
                "nmpelanggan = ? ,"+
                "almpelanggan1 = ? ,"+
                "almpelanggan2 = ? ,"+
                "emailpelanggan = ? "+
                "WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, pelanggan.getKdPelanggan());
            statement.setString(2, pelanggan.getNmPelanggan());
            statement.setString(3, pelanggan.getAlmPelanggan1());
            statement.setString(4, pelanggan.getAlmPelanggan2());
            statement.setString(5, pelanggan.getEmailPelanggan());
            statement.setString(6, pelanggan.getId());

            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

}
