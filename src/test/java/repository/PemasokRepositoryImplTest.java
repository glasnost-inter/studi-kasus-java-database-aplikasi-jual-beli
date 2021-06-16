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
    private PemasokRepository pemasokRepository;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();
        pemasokRepository = new PemasokRepositoryImpl(dataSource);
    }

    @Test
    void testAdd_success() {
        Pemasok pemasok = new Pemasok();

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Riau Webhost Indonesia"));
        pemasok.setNmPemasok("Riau Webhost Indonesia");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("riau.webhost.indonesia@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Lion Parcel"));
        pemasok.setNmPemasok("Lion Parcel");
        pemasok.setAlmPemasok1("Mega Bekasi");
        pemasok.setAlmPemasok2("Hypermall");
        pemasok.setEmailPemasok("lion.parcel@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

    }

    @Test
    void testAdd_failed() {
        Pemasok pemasok = new Pemasok();

        var strUUID = UUID.randomUUID().toString();
        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Master Mind Indonesia"));
        pemasok.setNmPemasok("Master Mind Indonesia");
        pemasok.setAlmPemasok1("Tebet Raya No 16");
        pemasok.setAlmPemasok2("Tebet Timur");
        pemasok.setEmailPemasok("master.mind.indonesia@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Herumindset"));
        pemasok.setNmPemasok("Herumindset");
        pemasok.setAlmPemasok1("Cihideung Ilir");
        pemasok.setAlmPemasok2("Pamulang");
        pemasok.setEmailPemasok("herumindset@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(-1,result);
    }

    @Test
    void testDelete_success() {
        Pemasok pemasok = new Pemasok();

        var strUUID = UUID.randomUUID().toString();
        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Pamulang Rental Mobil"));
        pemasok.setNmPemasok("Pamulang Rental Mobil");
        pemasok.setAlmPemasok1("Komplek Vila Pamulang");
        pemasok.setAlmPemasok2("Cluster Bellarosa");
        pemasok.setEmailPemasok("pamulang.rental.mobil@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        result = pemasokRepository.removeById(strUUID);
        assertEquals(1,result);
    }

    @Test
    void testDelete_failed() {
        Pemasok pemasok = new Pemasok();

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Stane Felica Interior Design"));
        pemasok.setNmPemasok("Stane Felica Interior Design");
        pemasok.setAlmPemasok1("The OAK Tower Appartment");
        pemasok.setAlmPemasok2("Jl Perintis Kemerdekaan");
        pemasok.setEmailPemasok("Stane.felica.interior.design@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        result = pemasokRepository.removeById(UUID.randomUUID().toString());
        assertEquals(0,result);
    }

    @Test
    void testUpdate_success() {
        Pemasok pemasok = new Pemasok();

        var strUUID = UUID.randomUUID().toString();
        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Wuling Arista Cimone"));
        pemasok.setNmPemasok("Wuling Arista Cimone");
        pemasok.setAlmPemasok1("Nn no.16");
        pemasok.setAlmPemasok2("RT 005/001 Cimone");
        pemasok.setEmailPemasok("wuling.arista.cimone@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Wuling Arista Cijantung"));
        pemasok.setNmPemasok("Wuling Arista Cijantung");
        pemasok.setAlmPemasok1("Nona no.16");
        pemasok.setAlmPemasok2("RT 005/001 Cimoneng");
        pemasok.setEmailPemasok("wuling.arista.cimoneng@gmail.com");

        result = pemasokRepository.updateById(pemasok);
        assertEquals(1,result);
    }

    @Test
    void testUpdate_failed() {
        Pemasok pemasok = new Pemasok();

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","IndiHome Telkom"));
        pemasok.setNmPemasok("IndiHome Telkom");
        pemasok.setAlmPemasok1("Jl. Mayor Kusmanto No.1");
        pemasok.setAlmPemasok2("Kedung Lumbu");
        pemasok.setEmailPemasok("indihome.telkom@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        pemasok.setId(UUID.randomUUID().toString());
        pemasok.setKdPemasok(CodeGenerator.input("PMK","IndiHome Telkom Indonesia"));
        pemasok.setNmPemasok("IndiHome Telkom Indonesia");
        pemasok.setAlmPemasok1("Jl Trunojoyo 31");
        pemasok.setAlmPemasok2("Kedung Lumbuew");
        pemasok.setEmailPemasok("indiHome.telkom.indonesia@gmail.com");

        result = pemasokRepository.updateById(pemasok);
        assertEquals(0,result);
    }

    @Test
    void testGetById_success() {
        Pemasok pemasok = new Pemasok();

        var strUUID = UUID.randomUUID().toString();

        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Asia Computer"));
        pemasok.setNmPemasok("Asia Computer");
        pemasok.setAlmPemasok1("Jl Kalimantan 68");
        pemasok.setAlmPemasok2("Laks L RE Martadinata 42-D");
        pemasok.setEmailPemasok("asia.computer@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        Pemasok[] pemasoks = pemasokRepository.getById(strUUID);
        assertEquals(1,pemasoks.length);
    }

    @Test
    void testGetAll_success() {

        Pemasok pemasok = new Pemasok();

        var strUUID = UUID.randomUUID().toString();

        pemasok.setId(strUUID);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Toho Computer"));
        pemasok.setNmPemasok("Toho Computer");
        pemasok.setAlmPemasok1("Jl Jend Sudirman Psr Bersama");
        pemasok.setAlmPemasok2("Pramuka Ruko Baru");
        pemasok.setEmailPemasok("toho.computer@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        Pemasok[] pemasoks = pemasokRepository.getAll();
        assertTrue(pemasoks.length > 0);
    }


}
