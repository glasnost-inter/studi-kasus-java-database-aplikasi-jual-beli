package repository;

import entity.Barang;

public interface BarangRepository {

    Barang[] getAll();

    Barang[] getById(String id);

    int add(Barang barang);

    int removeById(String id);

    int updateById(Barang barang);
}
