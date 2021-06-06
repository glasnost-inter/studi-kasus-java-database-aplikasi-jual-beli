package Repository;

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
             ResultSet resultSet = statement.executeQuery(sql);
        ) {

            List<Pemasok> list = new ArrayList<>();
            while (resultSet.next()){
                Pemasok pemasok = new Pemasok();
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
    public Pemasok[] getById(String KdBrg) {
        String sql = "select * from Pemasok where kdpemasok = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, KdBrg);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Pemasok pemasok = new Pemasok();
                    List<Pemasok> list = new ArrayList<>();
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
        String sql = "INSERT INTO Pemasok(kdpemasok,nmpemasok,almpemasok1,almpemasok2,emailpemasok) VALUES (?,?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, pemasok.getKdPemasok());
            statement.setString(2, pemasok.getNmPemasok());
            statement.setString(3, pemasok.getAlmPemasok1());
            statement.setString(4, pemasok.getAlmPemasok2());
            statement.setString(5, pemasok.getEmailPemasok());
            int rows = statement.executeUpdate();
            return rows;

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

    @Override
    public int removeById(String KdPemasok) {
        String sql = "DELETE FROM Pemasok WHERE KdPemasok = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, KdPemasok);
            int rows = statement.executeUpdate();
            return rows;
        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

    @Override
    public int updateById(Pemasok pemasok) {
        String sql = "update Pemasok " +
                "set nmpemasok = ? ,"+
                "almpemasok1 = ? ,"+
                "almpemasok2 = ? ,"+
                "emailpemasok = ? "+
                "WHERE kdpemasok = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, pemasok.getNmPemasok());
            statement.setString(2, pemasok.getAlmPemasok1());
            statement.setString(3, pemasok.getAlmPemasok2());
            statement.setString(4, pemasok.getEmailPemasok());
            statement.setString(5, pemasok.getKdPemasok());

            int rows = statement.executeUpdate();
            return rows;
        } catch (SQLException exception) {
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

}
