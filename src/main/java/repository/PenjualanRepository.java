package repository;

import entity.Penjualan;

public interface PenjualanRepository {
    Penjualan[] getAll();

    Penjualan[] getById(String id);

    int add(Penjualan penjualan);

    int removeById(String id);

    int removeAll();

    int updateById(Penjualan penjualan);
}
