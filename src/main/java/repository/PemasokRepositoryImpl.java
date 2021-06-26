package repository;

import entity.Pemasok;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PemasokRepositoryImpl implements PemasokRepository{

    private DataSource dataSource;

    public PemasokRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Pemasok[] getAll() {

        String sql = "select * from Pemasok";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ) {

            List<Pemasok> list = new ArrayList<>();
            while (resultSet.next()){
                Pemasok pemasok = new Pemasok();
                pemasok.setId(resultSet.getString("id"));
                pemasok.setKdPemasok(resultSet.getString("kdpemasok"));
                pemasok.setNmPemasok(resultSet.getString("nmpemasok"));
                pemasok.setAlmPemasok1(resultSet.getString("almpemasok1"));
                pemasok.setAlmPemasok2(resultSet.getString("almpemasok2"));
                pemasok.setEmailPemasok(resultSet.getString("emailpemasok"));

                list.add(pemasok);
            }

            return list.toArray(new Pemasok[]{});

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new Pemasok[0];
        }

    }

    @Override
    public Pemasok[] getById(String id) {
        String sql = "select * from Pemasok where id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Pemasok pemasok = new Pemasok();
                    List<Pemasok> list = new ArrayList<>();
                    pemasok.setId(resultSet.getString("id"));
                    pemasok.setKdPemasok(resultSet.getString("kdpemasok"));
                    pemasok.setNmPemasok(resultSet.getString("nmpemasok"));
                    pemasok.setAlmPemasok1(resultSet.getString("almpemasok1"));
                    pemasok.setAlmPemasok2(resultSet.getString("almpemasok2"));
                    pemasok.setEmailPemasok(resultSet.getString("emailpemasok"));

                    list.add(pemasok);
                    return list.toArray(new Pemasok[]{});
                }else{
                    return new Pemasok[]{};
                }
            } catch (SQLException exception) {
                //throw new RuntimeException(exception);
                System.out.println("Pesan eror : "+exception);
                return new Pemasok[0];
            }

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new Pemasok[0];
        }
    }

    @Override
    public int add(Pemasok pemasok) {
        String sql = "INSERT INTO Pemasok(id,kdpemasok,nmpemasok,almpemasok1,almpemasok2,emailpemasok) VALUES (?,?,?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, pemasok.getId());
            statement.setString(2, pemasok.getKdPemasok());
            statement.setString(3, pemasok.getNmPemasok());
            statement.setString(4, pemasok.getAlmPemasok1());
            statement.setString(5, pemasok.getAlmPemasok2());
            statement.setString(6, pemasok.getEmailPemasok());

            return statement.executeUpdate();

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

    @Override
    public int removeById(String id) {
        String sql = "DELETE FROM Pemasok WHERE id = ?";

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
        String sql = "DELETE FROM Pemasok";

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
    public int updateById(Pemasok pemasok) {
        String sql = "update Pemasok " +
                "set kdpemasok = ? ,"+
                "nmpemasok = ? ,"+
                "almpemasok1 = ? ,"+
                "almpemasok2 = ? ,"+
                "emailpemasok = ? "+
                "WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, pemasok.getKdPemasok());
            statement.setString(2, pemasok.getNmPemasok());
            statement.setString(3, pemasok.getAlmPemasok1());
            statement.setString(4, pemasok.getAlmPemasok2());
            statement.setString(5, pemasok.getEmailPemasok());
            statement.setString(6, pemasok.getId());

            return statement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

}
