package repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.Barang;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.CodeGenerator;
import util.DatabaseUtil;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BarangRepositoryImplTest {

    private HikariDataSource dataSource;
    private Barang barang;
    private BarangRepository barangRepository;

    private PembelianRepository pembelianRepository;
    private PenjualanRepository penjualanRepository;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();

        pembelianRepository = new PembelianRepositoryImpl(dataSource);
        pembelianRepository.removeAll();

        penjualanRepository = new PenjualanRepositoryImpl(dataSource);
        penjualanRepository.removeAll();

        barang = new Barang();
        barangRepository = new BarangRepositoryImpl(dataSource);
        barangRepository.removeAll();
    }

    @Test
    void testAddSuccess() {

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","RINSO ANTI NODA 900G 15246"));
        barang.setNmBrg("RINSO ANTI NODA 900G 15246");
        barang.setKdSat("pcs");
        barang.setJml(5);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","RINSO MOLTO 900G 15246"));
        barang.setNmBrg("RINSO MOLTO 900G 15246");
        barang.setKdSat("pcs");
        barang.setJml(2);

        result = barangRepository.add(barang);
        assertEquals(1,result);
    }

    @Test
    void testAddFailedToViolatePK() {

        var strUUID = UUID.randomUUID().toString();

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","ATTACK EASY 900G 16533"));
        barang.setNmBrg("ATTACK EASY 900G 16533");
        barang.setKdSat("pcs");
        barang.setJml(5);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","ATTACK SOFTENER 900G 17919"));
        barang.setNmBrg("ATTACK SOFTENER 900G 17919");
        barang.setKdSat("pcs");
        barang.setJml(6);

        result = barangRepository.add(barang);
        assertEquals(-1,result);
    }

    @Test
    void testAddFailedToViolateUniqConst() {

        var strUUID = UUID.randomUUID().toString();

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","ATTACK MAXIMIZER 900G 17919"));
        barang.setNmBrg("ATTACK MAXIMIZER 900G 17919");
        barang.setKdSat("pcs");
        barang.setJml(5);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","ATTACK MAXIMIZER 900G 17919"));
        barang.setNmBrg("ATTACK MAXIMIZER 900G 17919");
        barang.setKdSat("pcs");
        barang.setJml(6);

        result = barangRepository.add(barang);
        assertEquals(-1,result);

    }


    @Test
    void testDeleteSuccess() {

        var strUUID = UUID.randomUUID().toString();

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","ATTACK COLOUR 900G 17919"));
        barang.setNmBrg("ATTACK COLOUR 900G 17919");
        barang.setKdSat("pcs");
        barang.setJml(6);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        result = barangRepository.removeById(strUUID);
        assertEquals(1,result);
    }

    @Test
    void testDeleteFailedIdNotFound() {

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","SURF CLEAN FRESH 900G 12524"));
        barang.setNmBrg("SURF CLEAN FRESH 900G 12524");
        barang.setKdSat("pcs");
        barang.setJml(1);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        result = barangRepository.removeById(UUID.randomUUID().toString());
        assertEquals(0,result);
    }


    @Test
    void testUpdateSuccess() {

        var strUUID = UUID.randomUUID().toString();

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","SURF LEMON FRESH 900G 12524"));
        barang.setNmBrg("SURF LEMON FRESH 900G 12524");
        barang.setKdSat("pcs");
        barang.setJml(8);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","BUKRIM 5000 MERAH550G 5247"));
        barang.setNmBrg("BUKRIM 5000 MERAH550G 5247");
        barang.setKdSat("pcs");
        barang.setJml(18);


        result = barangRepository.updateById(barang);
        assertEquals(1,result);
    }

    @Test
    void testUpdateFailedIdNotFound() {

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","BUKRIM 5000 LEMON 550G 5247"));
        barang.setNmBrg("BUKRIM 5000 LEMON 550G 5247");
        barang.setKdSat("pcs");
        barang.setJml(7);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","WOW FRESH LIME 550G 5247"));
        barang.setNmBrg("WOW FRESH LIME 550G 5247");
        barang.setKdSat("pcs");
        barang.setJml(8);


        result = barangRepository.updateById(barang);
        assertEquals(0,result);
    }


    @Test
    void testGetByIdSuccess() {

        var strUUID = UUID.randomUUID().toString();

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","WOW EXOTIC FLOWER 550G 5247"));
        barang.setNmBrg("WOW EXOTIC FLOWER 550G 5247");
        barang.setKdSat("pcs");
        barang.setJml(4);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","WOW SEJUTA BUNGA 550G 5247"));
        barang.setNmBrg("WOW SEJUTA BUNGA 550G 5247");
        barang.setKdSat("pcs");
        barang.setJml(9);

        result = barangRepository.add(barang);
        assertEquals(1,result);

        Barang[] barangs = barangRepository.getById(strUUID);
        assertEquals(1,barangs.length);
    }

    @Test
    void testGetByIdFailedIdNotFound() {

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","BIMOLI 2 LT 25443"));
        barang.setNmBrg("BIMOLI 2 LT 25443");
        barang.setKdSat("pcs");
        barang.setJml(2);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","BIMOLI 1 LT 13118"));
        barang.setNmBrg("BIMOLI 1 LT 13118");
        barang.setKdSat("pcs");
        barang.setJml(1);

        result = barangRepository.add(barang);
        assertEquals(1,result);


        Barang[] barangs = barangRepository.getById(UUID.randomUUID().toString());
        assertEquals(0,barangs.length);
    }

    @Test
    void testGetAllSuccess() {

        var strUUID = UUID.randomUUID().toString();

        barang.setId(strUUID);
        barang.setKdBrg(CodeGenerator.input("BRG","FILMA 2 LT 22285"));
        barang.setNmBrg("FILMA 2 LT 22285");
        barang.setKdSat("pcs");
        barang.setJml(4);

        var result = barangRepository.add(barang);
        assertEquals(1,result);

        barang.setId(UUID.randomUUID().toString());
        barang.setKdBrg(CodeGenerator.input("BRG","FILMA 1 LT 11633"));
        barang.setNmBrg("FILMA 1 LT 11633");
        barang.setKdSat("pcs");
        barang.setJml(13);

        result = barangRepository.add(barang);
        assertEquals(1,result);


        Barang[] barangs = barangRepository.getAll();
        assertTrue(barangs.length > 0);
    }

    @Test
    void testGetAllFailedNoDataFound() {
        barangRepository.removeAll();
        Barang[] barangs = barangRepository.getAll();
        assertEquals(0, barangs.length);
    }

}
