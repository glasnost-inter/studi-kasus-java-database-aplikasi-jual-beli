package repository;

import entity.Pembelian;

public interface PembelianRepository {

    Pembelian[] getAll();

    Pembelian[] getById(String id);

    int add(Pembelian pembelian);

    int removeById(String id);

    int removeAll();

    int updateById(Pembelian pembelian);
}
