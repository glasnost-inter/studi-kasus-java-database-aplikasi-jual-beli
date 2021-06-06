package Repository;

import entity.Pemasok;

public interface PemasokRepository {

    Pemasok[] getAll();

    Pemasok[] getById(String KdPemasok);

    int add(Pemasok pemasok);

    int removeById(String KdPemasok);

    int updateById(Pemasok pemasok);

}
