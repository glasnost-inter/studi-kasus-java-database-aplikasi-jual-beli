package repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.Pemasok;
import entity.Pembelian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.CodeGenerator;
import util.DatabaseUtil;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PembelianRepositoryImplTest {
    private HikariDataSource dataSource;
    private PembelianRepository pembelianRepository;
    private PemasokRepository pemasokRepository;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();
        pembelianRepository = new PembelianRepositoryImpl(dataSource);
        pemasokRepository = new PemasokRepositoryImpl(dataSource);
    }

    @Test
    void testAdd_success() {

        Pemasok pemasok = new Pemasok();
        var strPemasokId = UUID.randomUUID().toString();
        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Jaya Baru Toko"));
        pemasok.setNmPemasok("Jaya Baru Toko");
        pemasok.setAlmPemasok1("Bau Messepee");
        pemasok.setAlmPemasok2("Pongtiku 5 A");
        pemasok.setEmailPemasok("jaya.baru.toko@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        Pembelian pembelian = new Pembelian();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        pembelian.setId(UUID.randomUUID().toString());
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Frans Kaiscpo"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

    }

    @Test
    void testAdd_failed() {
        /*
        begin memastikan referensial nyala
         */

        Pembelian pembelian = new Pembelian();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        pembelian.setId(UUID.randomUUID().toString());
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Bernie Madoff"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok("WrongSupplierCode");

        var result = pembelianRepository.add(pembelian);
        assertEquals(-1,result);

        /*
        end memastikan referensial nyala
         */

        /*
        begin memastikan primary key pembelian
         */

        Pemasok pemasok = new Pemasok();
        var strPemasokId = UUID.randomUUID().toString();
        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Garuda Indonesia PT Persero"));
        pemasok.setNmPemasok("Garuda Indonesia PT Persero");
        pemasok.setAlmPemasok1("Bandara Frans Kaiscpo");
        pemasok.setAlmPemasok2("Jl Flamboyan");
        pemasok.setEmailPemasok("garuda.indonesia@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        var strPembelianId = UUID.randomUUID().toString();
        pembelian.setId(strPembelianId);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Kon Marry"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        pembelian.setId(strPembelianId);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Jarrett Allen"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(-1,result);
    }

    @Test
    void testDelete_success() {
        Pemasok pemasok = new Pemasok();

        var strPemasokId = UUID.randomUUID().toString();
        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK", "PSKBS Kuta Binjei"));
        pemasok.setNmPemasok("PSKBS Kuta Binjei");
        pemasok.setAlmPemasok1("Pasar baru, kec.Kedondong");
        pemasok.setAlmPemasok2("kab.Pesawaran, prov.Lampung");
        pemasok.setEmailPemasok("pskbs.kuta.binjei@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1, result);

        Pembelian pembelian = new Pembelian();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        var strPembelianId = UUID.randomUUID().toString();
        pembelian.setId(strPembelianId);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Leroy Sané"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        result = pembelianRepository.removeById(strPembelianId);
        assertEquals(1,result);

    }

    @Test
    void testDelete_failed() {
        Pemasok pemasok = new Pemasok();
        var strPemasokId = UUID.randomUUID().toString();
        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Peureulak Raya"));
        pemasok.setNmPemasok("Peureulak Raya");
        pemasok.setAlmPemasok1("Jalan sangga buana II");
        pemasok.setAlmPemasok2("selatan no 10 palangkaraya");
        pemasok.setEmailPemasok("peureulak.raya@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        Pembelian pembelian = new Pembelian();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        var strPembelianId = UUID.randomUUID().toString();
        pembelian.setId(strPembelianId);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Marcel Sabitzer"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        result = pembelianRepository.removeById(UUID.randomUUID().toString());
        assertEquals(0,result);
    }

    @Test
    void testUpdate_success() {
        Pemasok pemasok = new Pemasok();

        var strPemasokId = UUID.randomUUID().toString();
        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK", "Leicester City"));
        pemasok.setNmPemasok("Leicester City");
        pemasok.setAlmPemasok1("Jln.manggis 4 no.14");
        pemasok.setAlmPemasok2("perumnas II parungpanjang,bogor");
        pemasok.setEmailPemasok("leicester.city@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1, result);

        Pembelian pembelian = new Pembelian();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        var strPembelianId = UUID.randomUUID().toString();
        pembelian.setId(strPembelianId);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Virgil van Dijk"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        strPemasokId = UUID.randomUUID().toString();
        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK", "YAYASAN HARAPAN IBU PAPUA"));
        pemasok.setNmPemasok("YAYASAN HARAPAN IBU PAPUA");
        pemasok.setAlmPemasok1("Jl.Ahmad Wongso Kel.Madurejo");
        pemasok.setAlmPemasok2("Pangkalan Bun Kalimantan Tengah");
        pemasok.setEmailPemasok("yayasan.harapan.ibu.papua@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1, result);

        pembelian.setId(strPembelianId);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Bruno Fernandes"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.updateById(pembelian);
        assertEquals(1,result);
    }

    @Test
    void testUpdate_failed() {
        Pemasok pemasok = new Pemasok();

        var strPemasokId = UUID.randomUUID().toString();
        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","LBH Apik Jakarta"));
        pemasok.setNmPemasok("LBH Apik Jakarta");
        pemasok.setAlmPemasok1("Dusun Batu Mangkung, Desa Ungga");
        pemasok.setAlmPemasok2("Praya Barat Daya, Lombok Tengah. NTB");
        pemasok.setEmailPemasok("lbh.apik.jakarta@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        Pembelian pembelian = new Pembelian();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        var strPembelianId = UUID.randomUUID().toString();
        pembelian.setId(strPembelianId);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Jack Grealish"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        pembelian.setId(strPembelianId);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Diogo Jota"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok("WrongSupplierID");

        result = pembelianRepository.updateById(pembelian);
        assertEquals(-1,result);
    }

    @Test
    void testGetById_success() {
        Pemasok pemasok = new Pemasok();

        var strPemasokId = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK", "WCC BALI"));
        pemasok.setNmPemasok("WCC BALI");
        pemasok.setAlmPemasok1("Jl tarmidi no 49");
        pemasok.setAlmPemasok2("samarinda Kalimantan timur");
        pemasok.setEmailPemasok("wcc.bali@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1, result);

        Pembelian pembelian = new Pembelian();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        var strPembelianId = UUID.randomUUID().toString();
        pembelian.setId(strPembelianId);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Sergio Agüero"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        Pembelian[] pembelians = pembelianRepository.getById(strPembelianId);
        assertEquals(1,pembelians.length);

    }

    @Test
    void testGetAll_success() {

        Pemasok pemasok = new Pemasok();

        var strPemasokId = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK", "ALIANSI PEREMPUAN SULAWESI TENGGARA"));
        pemasok.setNmPemasok("ALIANSI PEREMPUAN SULAWESI TENGGARA");
        pemasok.setAlmPemasok1("Ds. Mejasem Timur");
        pemasok.setAlmPemasok2("Kec. Kramat Kab. Tegal");
        pemasok.setEmailPemasok("aliansi.perempuan.sulawesi.tenggara@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        Pembelian pembelian = new Pembelian();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        var strPembelianId = UUID.randomUUID().toString();
        pembelian.setId(strPembelianId);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Dries Mertens"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        strPemasokId = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK", "Bayern Munich"));
        pemasok.setNmPemasok("Bayern Munich");
        pemasok.setAlmPemasok1("Jl. Flamboyan 4 desa tanjung sawit");
        pemasok.setAlmPemasok2("kec. Tapung kab. Kampar Riau Indonesia");
        pemasok.setEmailPemasok("bayern.munich@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        strPembelianId = UUID.randomUUID().toString();
        pembelian.setId(strPembelianId);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Jordan Bell"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        Pembelian[] pembelians = pembelianRepository.getAll();
        assertTrue(pembelians.length > 0);

    }
}
