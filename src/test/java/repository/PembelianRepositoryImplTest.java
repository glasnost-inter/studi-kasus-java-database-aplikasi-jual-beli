package repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.Pemasok;
import entity.Pembelian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.CodeGenerator;
import util.DatabaseUtil;

import java.util.UUID;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PembelianRepositoryImplTest {
    private HikariDataSource dataSource;
    private Pembelian pembelian;
    private PembelianRepository pembelianRepository;

    private Pemasok pemasok;
    private PemasokRepository pemasokRepository;

    private Date date;
    private java.sql.Date sqlDate;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();

        pembelian = new Pembelian();
        pembelianRepository = new PembelianRepositoryImpl(dataSource);
        pembelianRepository.removeAll();

        pemasok = new Pemasok();
        pemasokRepository = new PemasokRepositoryImpl(dataSource);
        pemasokRepository.removeAll();

        date = new Date();
        sqlDate = new java.sql.Date(date.getTime());
    }

    @Test
    void testAddSuccess() {

        /*insert master 1*/
        var strPemasokId1 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId1);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Puan Amal Hayati"));
        pemasok.setNmPemasok("Puan Amal Hayati");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("puan.amal.hayati@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);


        var strPemasokId2 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId2);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Yayasan Pulih"));
        pemasok.setNmPemasok("Yayasan Pulih");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("yayasan.pulih@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        /*insert master 1*/

        pembelian.setId(UUID.randomUUID().toString());
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Romelu Lukaku"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId1);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        pembelian.setId(UUID.randomUUID().toString());
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Harry Kane"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId2);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);
    }

    @Test
    void testAddFailedToViolatePK() {

        /*insert master 1*/
        var strPemasokId = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Majelis Kesejahteraan Sosial"));
        pemasok.setNmPemasok("Majelis Kesejahteraan Sosial");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("majelis.kesejahteraan.sosial@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);
        /*insert master 1*/

        var strUUID = UUID.randomUUID().toString();

        pembelian.setId(strUUID);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Bruno Fernandes"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        pembelian.setId(strUUID);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Son Heung-min"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(-1,result);
    }

    @Test
    void testAddFailedParentKeyNotFound() {

        /*insert master 1*/
        var strPemasokId = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","HWDI"));
        pemasok.setNmPemasok("HWDI");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("hwdi@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);
        /*insert master 1*/

        var strUUID = UUID.randomUUID().toString();

        pembelian.setId(strUUID);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Alphonso Davies"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok("WrongParentId");

        result = pembelianRepository.add(pembelian);
        assertEquals(-1,result);
    }

    @Test
    void testAddFailedToViolateUniqConst() {

        /*insert master 1*/

        /*insert master 1*/

        /*insert child 1*/

        /*insert child 1*/

        /*insert child 1 with same value column to violate uniq*/

        /*insert child 1 with same value column to violate uniq*/

    }


    @Test
    void testDeleteSuccess() {

        /*insert master 1*/
        var strPemasokId1 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId1);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","SBMI Pusat"));
        pemasok.setNmPemasok("SBMI Pusat");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("sbmi.pusat@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        var strPemasokId2 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId2);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","OPSI DKI"));
        pemasok.setNmPemasok("OPSI DKI");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("opsi.dki@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        /*insert master 1*/

        var strUUID = UUID.randomUUID().toString();

        pembelian.setId(strUUID);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Ciro Immobile"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId1);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        result = pembelianRepository.removeById(strUUID);
        assertEquals(1,result);
    }

    @Test
    void testDeleteFailedIdNotFound() {

        /*insert master 1*/
        var strPemasokId = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","DPP Wanita Katolik RI"));
        pemasok.setNmPemasok("DPP Wanita Katolik RI");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("dpp.wanita.katolik.ri@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        /*insert master 1*/

        pembelian.setId(UUID.randomUUID().toString());
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Zlatan Ibrahimovic"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        result = pembelianRepository.removeById(UUID.randomUUID().toString());
        assertEquals(0,result);
    }

    @Test
    void testDeleteFailedChildFound() {

        /*insert master 1*/
        var strPemasokId = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","LENSA Sukabumi"));
        pemasok.setNmPemasok("LENSA Sukabumi");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("lensa.sukabumi@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        /*insert master 1*/

        pembelian.setId(UUID.randomUUID().toString());
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Becker Alisson"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        result = pemasokRepository.removeById(strPemasokId);
        assertEquals(-1,result);
    }

    @Test
    void testUpdateSuccess() {

        /*insert master 1*/
        var strPemasokId1 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId1);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","SAPA Institut"));
        pemasok.setNmPemasok("SAPA Institut");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("sapa.institut@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        var strPemasokId2 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId2);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","WCC Mawar Balqis"));
        pemasok.setNmPemasok("WCC Mawar Balqis");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("wcc.mawar.balqis@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);
        /*insert master 1*/

        var strUUID = UUID.randomUUID().toString();
        pembelian.setId(strUUID);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Raheem Sterling"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId1);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        pembelian.setId(strUUID);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Jan Oblak"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId1);


        result = pembelianRepository.updateById(pembelian);
        assertEquals(1,result);
    }

    @Test
    void testUpdateFailedIdNotFound() {

        /*insert master 1*/
        var strPemasokId1 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId1);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","WCC Durebang Pasundan"));
        pemasok.setNmPemasok("WCC Durebang Pasundan");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("wcc.durebang.pasundan@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        var strPemasokId2 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId2);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","SBMI Karawang"));
        pemasok.setNmPemasok("SBMI Karawang");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("sbmi.karawang@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);
        /*insert master 1*/

        pembelian.setId(UUID.randomUUID().toString());
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Jadon Sancho"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId1);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        pembelian.setId(UUID.randomUUID().toString());
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Timo Werner"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId2);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        pembelian.setId(UUID.randomUUID().toString());
        pembelian.setNoPembelian(CodeGenerator.input("BLI","David Alaba"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok("WrongParentId");


        result = pembelianRepository.updateById(pembelian);
        assertEquals(0,result);
    }


    @Test
    void testUpdateFailedParentNotFound() {

        /*insert master 1*/
        var strPemasokId1 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId1);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Puan Amal Hayati Cipasung"));
        pemasok.setNmPemasok("Puan Amal Hayati Cipasung");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("puan.amal.hayati.cipasung@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        var strPemasokId2 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId2);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Jari Relawan Independen"));
        pemasok.setNmPemasok("Jari Relawan Independen");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("yayasan.jari.relawan.independen@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);
        /*insert master 1*/

        var strUUID = UUID.randomUUID().toString();

        pembelian.setId(strUUID);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Alejandro Papu Gómez"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId1);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        pembelian.setId(UUID.randomUUID().toString());
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Jordan Henderson"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId2);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        pembelian.setId(strUUID);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Andrew Robertson"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok("WrongParentId");


        result = pembelianRepository.updateById(pembelian);
        assertEquals(-1,result);
    }

    @Test
    void testUpdateFailedToViolateUniqConst() {

        /*insert master 1*/

        /*insert master 1*/

        /*insert child 1*/

        /*insert child 1*/

        /*insert child 1 with same value column to violate uniq*/

        /*insert child 1 with same value column to violate uniq*/

    }

    @Test
    void testGetByIdSuccess() {

        /*insert master 1*/
        var strPemasokId1 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId1);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","LBH Apik Aceh"));
        pemasok.setNmPemasok("LBH Apik Aceh");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("lbh.apik.aceh@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        var strPemasokId2 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId2);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","SPEK-HAM"));
        pemasok.setNmPemasok("SPEK-HAM");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("SPEK-HAM@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);
        /*insert master 1*/

        var strUUID = UUID.randomUUID().toString();

        pembelian.setId(strUUID);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Jamie Vardy"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId1);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        pembelian.setId(UUID.randomUUID().toString());
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Pierre-Emerick Aubameyang"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId2);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        Pembelian[] pembelians = pembelianRepository.getById(strUUID);
        assertEquals(1,pembelians.length);
    }

    @Test
    void testGetByIdFailedIdNotFound() {

        /*insert master 1*/
        var strPemasokId1 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId1);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","Legal Resources Center"));
        pemasok.setNmPemasok("Legal Resources Center");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("lrc.kjham@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        var strPemasokId2 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId2);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","UPIPA Wonosobo"));
        pemasok.setNmPemasok("UPIPA Wonosobo");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("upipa.wonosobo@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);
        /*insert master 1*/

        var strUUID = UUID.randomUUID().toString();

        pembelian.setId(strUUID);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","João Félix"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId1);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        pembelian.setId(UUID.randomUUID().toString());
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Ángel Di María"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId2);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        Pembelian[] pembelians = pembelianRepository.getById(UUID.randomUUID().toString());
        assertEquals(0,pembelians.length);
    }

    @Test
    void testGetAllSuccess() {

        /*insert master 1*/
        var strPemasokId1 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId1);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","WCC Lentera Perempuan Purwokerto"));
        pemasok.setNmPemasok("WCC Lentera Perempuan Purwokerto");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("wcc.lentera.purwokerto@gmail.com");

        var result = pemasokRepository.add(pemasok);
        assertEquals(1,result);

        var strPemasokId2 = UUID.randomUUID().toString();

        pemasok.setId(strPemasokId2);
        pemasok.setKdPemasok(CodeGenerator.input("PMK","LBH APIK Semarang"));
        pemasok.setNmPemasok("LBH APIK Semarang");
        pemasok.setAlmPemasok1("Jl Melati I");
        pemasok.setAlmPemasok2("Sidomulyo");
        pemasok.setEmailPemasok("lbh.apik.semarang@gmail.com");

        result = pemasokRepository.add(pemasok);
        assertEquals(1,result);
        /*insert master 1*/

        var strUUID = UUID.randomUUID().toString();

        pembelian.setId(strUUID);
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Paulo Dybala"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId1);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);

        pembelian.setId(UUID.randomUUID().toString());
        pembelian.setNoPembelian(CodeGenerator.input("BLI","Leon Goretzka"));
        pembelian.setTglPembelian(sqlDate);
        pembelian.setKdPemasok(strPemasokId2);

        result = pembelianRepository.add(pembelian);
        assertEquals(1,result);


        Pembelian[] pembelians = pembelianRepository.getAll();
        assertTrue(pembelians.length > 0);
    }

    @Test
    void testGetAllFailedNoDataFound() {
        pembelianRepository.removeAll();
        pemasokRepository.removeAll();
        Pembelian[] pembelians = pembelianRepository.getAll();
        assertEquals(0, pembelians.length);
    }

}
