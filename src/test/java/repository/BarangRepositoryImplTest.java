package repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.Barang;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.CodeGenerator;
import util.DatabaseUtil;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BarangRepositoryImplTest {

    private HikariDataSource dataSource;
    private BarangRepository barangRepository;


    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();
        barangRepository = new BarangRepositoryImpl(dataSource);
    }

    @Test
    void testAdd1_success() {
        Barang barang = new Barang();

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","Laptop"));
        barang.setNmBrg("Laptop");
        barang.setKdSat("pcs");
        barang.setJml(5);

        var result = barangRepository.add(barang);
        assertEquals(1,result);
    }

    @Test
    void testAdd2_success() {
        Barang barang = new Barang();

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","Monitor"));
        barang.setNmBrg("Monitor");
        barang.setKdSat("pcs");
        barang.setJml(2);

        var result = barangRepository.add(barang);
        assertEquals(1,result);
    }

    @Test
    void testAdd3_failed() {
        Barang barang = new Barang();

        var strUUID = UUID.randomUUID().toString();

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","Laptop Toshiba"));
        barang.setNmBrg("Laptop Toshiba");
        barang.setKdSat("pcs");
        barang.setJml(5);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","Kantor"));
        barang.setNmBrg("Kantor");
        barang.setKdSat("pcs");
        barang.setJml(6);

        result = barangRepository.add(barang);
        assertEquals(-1,result);

    }


    @Test
    void testDelete1_success() {

        Barang barang = new Barang();

        var strUUID = UUID.randomUUID().toString();

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","Mouse"));
        barang.setNmBrg("Mouse");
        barang.setKdSat("pcs");
        barang.setJml(6);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        result = barangRepository.removeById(strUUID);
        assertEquals(1,result);
    }

    @Test
    void testDelete2_success() {

        Barang barang = new Barang();

        var strUUID = UUID.randomUUID().toString();

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","Pulpen"));
        barang.setNmBrg("Pulpen");
        barang.setKdSat("pcs");
        barang.setJml(10);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        result = barangRepository.removeById(strUUID);
        assertEquals(1,result);
    }

    @Test
    void testDelete3_failed() {

        Barang barang = new Barang();

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","Kabel Roll"));
        barang.setNmBrg("Kabel Roll");
        barang.setKdSat("pcs");
        barang.setJml(1);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        result = barangRepository.removeById(UUID.randomUUID().toString());
        assertEquals(0,result);
    }


    @Test
    void testUpdate1_success() {

        Barang barang = new Barang();

        var strUUID = UUID.randomUUID().toString();

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","Table"));
        barang.setNmBrg("Table");
        barang.setKdSat("pcs");
        barang.setJml(8);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","Tablet"));
        barang.setNmBrg("Tablet");
        barang.setKdSat("pcs");
        barang.setJml(18);


        result = barangRepository.updateById(barang);
        assertEquals(1,result);
    }

    @Test
    void testUpdate2_success() {

        Barang barang = new Barang();

        var strUUID = UUID.randomUUID().toString();

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","Buku"));
        barang.setNmBrg("Buku");
        barang.setKdSat("pcs");
        barang.setJml(11);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","Manual Book"));
        barang.setNmBrg("Manual Book");
        barang.setKdSat("pcs");
        barang.setJml(3);


        result = barangRepository.updateById(barang);
        assertEquals(1,result);
    }

    @Test
    void testUpdate3_failed() {

        Barang barang = new Barang();

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","Speaker"));
        barang.setNmBrg("Speaker");
        barang.setKdSat("pcs");
        barang.setJml(7);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","Speaker Aktif"));
        barang.setNmBrg("Speaker Aktif");
        barang.setKdSat("pcs");
        barang.setJml(8);


        result = barangRepository.updateById(barang);
        assertEquals(0,result);
    }


    @Test
    void testGetById1_success() {

        Barang barang = new Barang();

        var strUUID = UUID.randomUUID().toString();

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","Karpet"));
        barang.setNmBrg("Karpet");
        barang.setKdSat("pcs");
        barang.setJml(4);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","Susu UHT"));
        barang.setNmBrg("Susu UHT");
        barang.setKdSat("pcs");
        barang.setJml(9);

        result = barangRepository.add(barang);
        assertEquals(1,result);


        Barang[] barangs = barangRepository.getById(strUUID);
        assertEquals(1,barangs.length);
    }

    @Test
    void testGetById2_failed() {

        Barang barang = new Barang();

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","Pintu"));
        barang.setNmBrg("Pintu");
        barang.setKdSat("pcs");
        barang.setJml(2);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","Tas"));
        barang.setNmBrg("Tas");
        barang.setKdSat("pcs");
        barang.setJml(1);

        result = barangRepository.add(barang);
        assertEquals(1,result);


        Barang[] barangs = barangRepository.getById(UUID.randomUUID().toString());
        assertEquals(0,barangs.length);
    }

    @Test
    void testGetAll1_success() {

        Barang barang = new Barang();

        var strUUID = UUID.randomUUID().toString();

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","Kursi"));
        barang.setNmBrg("Kursi");
        barang.setKdSat("pcs");
        barang.setJml(4);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","test"));
        barang.setNmBrg("Jendela");
        barang.setKdSat("pcs");
        barang.setJml(13);

        result = barangRepository.add(barang);
        assertEquals(1,result);


        Barang[] barangs = barangRepository.getById(strUUID);
        assertTrue(barangs.length > 0);
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
    }
}
