package Repository;

import entity.Barang;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarangRepositoryImpl implements BarangRepository{

    private DataSource dataSource;

    public BarangRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Barang[] getAll() {

        String sql = "select * from BARANG";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
        ) {

            List<Barang> list = new ArrayList<>();
            while (resultSet.next()){
                Barang barang = new Barang();
                barang.setKdBrg(resultSet.getString("kdbrg"));
                barang.setNmBrg(resultSet.getString("nmbrg"));
                barang.setKdSat(resultSet.getString("kdsat"));
                barang.setJml(resultSet.getInt("jml"));

                list.add(barang);
            }

            return list.toArray(new Barang[]{});

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new Barang[0];
        }

    }

    @Override
    public Barang[] getById(String KdBrg) {
        String sql = "select * from BARANG where KdBrg = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, KdBrg);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Barang barang = new Barang();
                    List<Barang> list = new ArrayList<>();
                    barang.setKdBrg(resultSet.getString("kdbrg"));
                    barang.setNmBrg(resultSet.getString("nmbrg"));
                    barang.setKdSat(resultSet.getString("kdsat"));
                    barang.setJml(resultSet.getInt("jml"));

                    list.add(barang);
                    return list.toArray(new Barang[]{});
                }else{
                    return new Barang[]{};
                }
            } catch (SQLException exception) {
                //throw new RuntimeException(exception);
                System.out.println("Pesan eror : "+exception);
                return new Barang[0];
            }

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return new Barang[0];
        }
    }

    @Override
    public int add(Barang barang) {
        String sql = "INSERT INTO barang(kdbrg,nmbrg,kdsat,jml) VALUES (?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, barang.getKdBrg());
            statement.setString(2, barang.getNmBrg());
            statement.setString(3, barang.getKdSat());
            statement.setInt(4, barang.getJml());
            int rows = statement.executeUpdate();
            return rows;

        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

    @Override
    public int removeById(String KdBrg) {
        String sql = "DELETE FROM barang WHERE kdbrg = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, KdBrg);
            int rows = statement.executeUpdate();
            return rows;
        } catch (SQLException exception) {
            //throw new RuntimeException(exception);
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

    @Override
    public int updateById(Barang barang) {
        String sql = "update barang " +
                     "set nmbrg = ? ,"+
                         "kdsat = ? ,"+
                         "jml = ? "+
                     "WHERE kdbrg = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {

            statement.setString(1, barang.getNmBrg());
            statement.setString(2, barang.getKdSat());
            statement.setInt(3, barang.getJml());
            statement.setString(4, barang.getKdBrg());
            int rows = statement.executeUpdate();
            return rows;
        } catch (SQLException exception) {
            System.out.println("Pesan eror : "+exception);
            return -1;
        }
    }

}
