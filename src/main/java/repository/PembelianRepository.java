package repository;

import entity.Pembelian;

public interface PembelianRepository {

    Pembelian[] getAll();

    Pembelian[] getById(String id);

    int add(Pembelian pembelian);

    int removeById(String id);

    int updateById(Pembelian pembelian);
}
