package repository;

import entity.DtPenjualan;

public interface DtPenjualanRepository {
    DtPenjualan[] getAll();

    DtPenjualan[] getById(String id);

    int add(DtPenjualan dtPenjualan);

    int removeById(String id);

    int removeAll();

    int updateById(DtPenjualan dtPenjualan);
}
