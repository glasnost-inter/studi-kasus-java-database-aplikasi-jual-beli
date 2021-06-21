package repository;

import entity.DtPembelian;

public interface DtPembelianRepository {

    DtPembelian[] getAll();

    DtPembelian[] getById(String id);

    int add(DtPembelian dtPembelian);

    int removeById(String id);

    int updateById(DtPembelian dtPembelian);
}
