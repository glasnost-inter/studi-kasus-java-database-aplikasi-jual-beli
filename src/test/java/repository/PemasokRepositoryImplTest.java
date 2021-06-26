package repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.Pemasok;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.CodeGenerator;
import util.DatabaseUtil;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PemasokRepositoryImplTest {

    private HikariDataSource dataSource;
    private Pemasok pemasok;
    private PemasokRepository pemasokRepository;
    private PembelianRepository pembelianRepository;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();

        pembelianRepository = new PembelianRepositoryImpl(dataSource);
        pembelianRepository.removeAll();

        pemasok = new Pemasok();
        pemasokRepository = new PemasokRepositoryImpl(dataSource);
        pemasokRepository.removeAll();
    }

    @Test
    void testAddSuccess() {

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","LBH Apik Aceh"));
        pemasok.setNmPemasok("LBH Apik Aceh");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("lbh.apik.aceh@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","RPUK Banda Aceh"));
        pemasok.setNmPemasok("RPUK Banda Aceh");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("rpuk.banda.aceh@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);
    }

    @Test
    void testAddFailedToViolatePK() {

        var strUUID = UUID.randomUUID().toString();

        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Himpunan Serikat Perempuan Indonesia"));
        pemasok.setNmPemasok("Himpunan Serikat Perempuan Indonesia");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("himpunan.serikat.perempuan.indonesia@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","LBH APIK Medan"));
        pemasok.setNmPemasok("LBH APIK Medan");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("lbh.apik.medan@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(-1,result);
    }

    @Test
    void testAddFailedToViolateUniqConst() {

        var strUUID = UUID.randomUUID().toString();

        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Serikat Perempuan Independen"));
        pemasok.setNmPemasok("Serikat Perempuan Independen");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("labuhanbatu@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Perkumpulan Peduli Medan"));
        pemasok.setNmPemasok("Serikat Perempuan Independen");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("perkumpulan.peduli.medan@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(-1,result);

    }


    @Test
    void testDeleteSuccess() {

        var strUUID = UUID.randomUUID().toString();

        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Aliansi Sumut Bersatu (ASB) Medan"));
        pemasok.setNmPemasok("Aliansi Sumut Bersatu (ASB) Medan");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("liansi.sumut.bersatu.medan@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        result = pemasokRepository.removeById(strUUID);
        assertEquals(1,result);
    }

    @Test
    void testDeleteFailedIdNotFound() {

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Embun Pelangi"));
        pemasok.setNmPemasok("Embun Pelangi");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("embun.pelangi@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        result = pemasokRepository.removeById(UUID.randomUUID().toString());
        assertEquals(0,result);
    }


    @Test
    void testUpdateSuccess() {

        var strUUID = UUID.randomUUID().toString();

        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Aliansi Perempuan Merangin"));
        pemasok.setNmPemasok("Aliansi Perempuan Merangin");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("aliansi.perempuan.merangin@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","WCC Nurani Perempuan"));
        pemasok.setNmPemasok("WCC Nurani Perempuan");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("wcc.nurani.perempuan@gmail.com");


        result = pemasokRepository.updateById(pemasok);
        assertEquals(1,result);
    }

    @Test
    void testUpdateFailedIdNotFound() {

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Cahaya Perempuan WCC"));
        pemasok.setNmPemasok("Cahaya Perempuan WCC");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("cahaya.perempuan.wcc@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Pendidikan Untuk Perempuan dan Anak"));
        pemasok.setNmPemasok("Pendidikan Untuk Perempuan dan Anak");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("pendidikan.untuk.perempuan.dan.anak@gmail.com");


        result = pemasokRepository.updateById(pemasok);
        assertEquals(0,result);
    }


    @Test
    void testGetByIdSuccess() {

        var strUUID = UUID.randomUUID().toString();

        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","WCC Palembang"));
        pemasok.setNmPemasok("WCC Palembang");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("wcc.palembang@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","LBH Palembang"));
        pemasok.setNmPemasok("LBH Palembang");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("lbh.palembang@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        Pemasok[] pemasoks = pemasokRepository.getById(strUUID);
        assertEquals(1,pemasoks.length);
    }

    @Test
    void testGetByIdFailedIdNotFound() {

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","LSM Perlindungan dan Pemberdayaan"));
        pemasok.setNmPemasok("LSM Perlindungan dan Pemberdayaan");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("lsm.perlindungan@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Lembaga Advokasi Perempuan Damar"));
        pemasok.setNmPemasok("Lembaga Advokasi Perempuan Damar");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("lembaga.advokasi.perempuan.damar@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);


        Pemasok[] pemasoks = pemasokRepository.getById(UUID.randomUUID().toString());
        assertEquals(0,pemasoks.length);
    }

    @Test
    void testGetAllSuccess() {

        var strUUID = UUID.randomUUID().toString();

        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","LBH APIK Banten"));
        pemasok.setNmPemasok("LBH APIK Banten");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("lbh.apik.banten@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Yayasan LBH Keadilan Banten"));
        pemasok.setNmPemasok("Yayasan LBH Keadilan Banten");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("yayasan.lbh.keadilan.banten@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);


        Pemasok[] pemasoks = pemasokRepository.getAll();
        assertTrue(pemasoks.length > 0);
    }

    @Test
    void testGetAllFailedNoDataFound() {
        pemasokRepository.removeAll();
        Pemasok[] pemasoks = pemasokRepository.getAll();
        assertEquals(0, pemasoks.length);
    }

}




