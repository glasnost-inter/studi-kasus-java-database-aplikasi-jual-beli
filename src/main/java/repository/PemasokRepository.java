package repository;

import entity.Pemasok;

public interface PemasokRepository {

    Pemasok[] getAll();

    Pemasok[] getById(String id);

    int add(Pemasok pemasok);

    int removeById(String id);

    int removeAll();

    int updateById(Pemasok pemasok);

}
