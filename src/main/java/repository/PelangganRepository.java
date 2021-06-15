package repository;

import entity.Pelanggan;

public interface PelangganRepository {

    Pelanggan[] getAll();

    Pelanggan[] getById(String id);

    int add(Pelanggan pelanggan);

    int removeById(String id);

    int updateById(Pelanggan pelanggan);

}
