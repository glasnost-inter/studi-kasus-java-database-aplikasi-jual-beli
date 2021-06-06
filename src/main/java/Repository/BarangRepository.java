package Repository;

import entity.Barang;

public interface BarangRepository {

    Barang[] getAll();

    Barang[] getById(String KdBrg);

    int add(Barang barang);

    int removeById(String KdBrg);

    int updateById(Barang barang);
}
