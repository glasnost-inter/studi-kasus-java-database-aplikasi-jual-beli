package Repository;

import entity.Pelanggan;

public interface PelangganRepository {

    Pelanggan[] getAll();

    Pelanggan[] getById(String KdPelanggan);

    int add(Pelanggan pelanggan);

    int removeById(String KdPelanggan);

    int updateById(Pelanggan pelanggan);

}
