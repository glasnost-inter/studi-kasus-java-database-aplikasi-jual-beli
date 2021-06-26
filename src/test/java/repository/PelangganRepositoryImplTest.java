package repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.Pelanggan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.CodeGenerator;
import util.DatabaseUtil;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PelangganRepositoryImplTest {
    private HikariDataSource dataSource;

    private Pelanggan pelanggan;
    private PelangganRepository pelangganRepository;

    private PenjualanRepository penjualanRepository;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();

        penjualanRepository = new PenjualanRepositoryImpl(dataSource);
        penjualanRepository.removeAll();

        pelanggan = new Pelanggan();
        pelangganRepository = new PelangganRepositoryImpl(dataSource);
        pelangganRepository.removeAll();


    }

    @Test
    void testAddSuccess() {

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Robert Lewandowski"));
        pelanggan.setNmPelanggan("Robert Lewandowski");
        pelanggan.setAlmPelanggan1("Jln. Tgk. Daud No. 147 ");
        pelanggan.setAlmPelanggan2("Bukit Panggoi Indah Kota Lhokseumawe");
        pelanggan.setEmailPelanggan("robert.lewandowski@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Lionel Messi"));
        pelanggan.setNmPelanggan("Lionel Messi");
        pelanggan.setAlmPelanggan1("Jl. Sisingamangaraja No. 39/123");
        pelanggan.setAlmPelanggan2("Kampung Mulia, Banda Aceh");
        pelanggan.setEmailPelanggan("lionel.messi@gmail.com");

        result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);
    }

    @Test
    void testAddFailedToViolatePK() {

        var strUUID = UUID.randomUUID().toString();

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Cristiano Ronaldo"));
        pelanggan.setNmPelanggan("Cristiano Ronaldo");
        pelanggan.setAlmPelanggan1("Jl. Thamrin No.53A");
        pelanggan.setAlmPelanggan2("Kab. Deli Serdang Sumatera Utara");
        pelanggan.setEmailPelanggan("cristiano.ronaldo@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Kevin De Bruyne"));
        pelanggan.setNmPelanggan("Kevin De Bruyne");
        pelanggan.setAlmPelanggan1("Jl. Jermal V No. 1 C");
        pelanggan.setAlmPelanggan2("Kelurahan Denai Kecamatan Medan Denai");
        pelanggan.setEmailPelanggan("kevin.de.bruyne@gmail.com");

        result = pelangganRepository.add(pelanggan);
        assertEquals(-1,result);
    }

    @Test
    void testAddFailedToViolateUniqConst() {

        var strUUID = UUID.randomUUID().toString();

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Sadio Mané"));
        pelanggan.setNmPelanggan("Sadio Mané");
        pelanggan.setAlmPelanggan1("Perumahan Ganda Asri N 19 J");
        pelanggan.setAlmPelanggan2("Kabupaten Labuhanbatu Provinsi Sumatera Utara");
        pelanggan.setEmailPelanggan("sadio.mané@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Sadio Mané"));
        pelanggan.setNmPelanggan("Sadio Mané");
        pelanggan.setAlmPelanggan1("Jl. Ramli II No. 21");
        pelanggan.setAlmPelanggan2("Perumnas Simalingkar");
        pelanggan.setEmailPelanggan("erling.braut.haaland@gmail.com");

        result = pelangganRepository.add(pelanggan);
        assertEquals(-1,result);

    }


    @Test
    void testDeleteSuccess() {

        var strUUID = UUID.randomUUID().toString();

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Kylian Mbappé"));
        pelanggan.setNmPelanggan("Kylian Mbappé");
        pelanggan.setAlmPelanggan1("Jalan Pasar VII No. 156");
        pelanggan.setAlmPelanggan2("Padang Bulan. Medan");
        pelanggan.setEmailPelanggan("kylian.mbappé@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        result = pelangganRepository.removeById(strUUID);
        assertEquals(1,result);
    }

    @Test
    void testDeleteFailedIdNotFound() {

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Mohamed Salah"));
        pelanggan.setNmPelanggan("Mohamed Salah");
        pelanggan.setAlmPelanggan1("Perum Anggrek Permai Blok K No 22 Baloi Kecamatan");
        pelanggan.setAlmPelanggan2("Lubuk Baja Kota Batam Kepulauan Riau");
        pelanggan.setEmailPelanggan("mohamed.salah@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        result = pelangganRepository.removeById(UUID.randomUUID().toString());
        assertEquals(0,result);
    }


    @Test
    void testUpdateSuccess() {

        var strUUID = UUID.randomUUID().toString();

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Neymar"));
        pelanggan.setNmPelanggan("Neymar");
        pelanggan.setAlmPelanggan1("Dsn Marga Mulya/I Desa Pulau Tujuh");
        pelanggan.setAlmPelanggan2("Kec. Pamenang Barat");
        pelanggan.setEmailPelanggan("neymar@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Virgil van Dijk"));
        pelanggan.setNmPelanggan("Virgil van Dijk");
        pelanggan.setAlmPelanggan1("Jl. Pekanbaru II No. 28 D");
        pelanggan.setAlmPelanggan2("Kec. Padang Utara, Kota Padang");
        pelanggan.setEmailPelanggan("virgil.van.dijk@gmail.com");


        result = pelangganRepository.updateById(pelanggan);
        assertEquals(1,result);
    }

    @Test
    void testUpdateFailedIdNotFound() {

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Joshua Kimmich"));
        pelanggan.setNmPelanggan("Joshua Kimmich");
        pelanggan.setAlmPelanggan1("Jln Indragiri 1 No.3 Kel. Padang Harapan");
        pelanggan.setAlmPelanggan2("Kec. Gading Cempaka Kota Bengkulu");
        pelanggan.setEmailPelanggan("joshua.kimmich@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Karim Benzema"));
        pelanggan.setNmPelanggan("Karim Benzema");
        pelanggan.setAlmPelanggan1("Jl. Kesehatan 1 No. 06 RT 02");
        pelanggan.setAlmPelanggan2("Anggut Bawah Kecamatan Ratu Kota Bengkulu");
        pelanggan.setEmailPelanggan("karim.benzema@gmail.com");


        result = pelangganRepository.updateById(pelanggan);
        assertEquals(0,result);
    }


    @Test
    void testGetByIdSuccess() {

        var strUUID = UUID.randomUUID().toString();

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Sergio Ramos"));
        pelanggan.setNmPelanggan("Sergio Ramos");
        pelanggan.setAlmPelanggan1("Jalan Musi 3 Blok H68");
        pelanggan.setAlmPelanggan2("Kecamatan Ilir Barat I Palembang");
        pelanggan.setEmailPelanggan("sergio.ramos@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Thomas Müller"));
        pelanggan.setNmPelanggan("Thomas Müller");
        pelanggan.setAlmPelanggan1("Jl. Bidar Blok B no.6");
        pelanggan.setAlmPelanggan2("Kampus Palembang 30137");
        pelanggan.setEmailPelanggan("thomas.müller@gmail.com");

        result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        Pelanggan[] pelanggans = pelangganRepository.getById(strUUID);
        assertEquals(1,pelanggans.length);
    }

    @Test
    void testGetByIdFailedIdNotFound() {

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Manuel Neuer"));
        pelanggan.setNmPelanggan("Manuel Neuer");
        pelanggan.setAlmPelanggan1("Jl. Kenangan No. 253");
        pelanggan.setAlmPelanggan2("Pangkal Pinang");
        pelanggan.setEmailPelanggan("manuel.neuer@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Serge Gnabry"));
        pelanggan.setNmPelanggan("Serge Gnabry");
        pelanggan.setAlmPelanggan1("Jl. MH. Thamrin No. 14 Gotong Royong");
        pelanggan.setAlmPelanggan2("Tanjung Karang Pusat, Bandar Lampung");
        pelanggan.setEmailPelanggan("serge.gnabry@gmail.com");

        result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);


        Pelanggan[] pelanggans = pelangganRepository.getById(UUID.randomUUID().toString());
        assertEquals(0,pelanggans.length);
    }

    @Test
    void testGetAllSuccess() {

        var strUUID = UUID.randomUUID().toString();

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Thiago Alcântara"));
        pelanggan.setNmPelanggan("Thiago Alcântara");
        pelanggan.setAlmPelanggan1("Tanjung Priok");
        pelanggan.setAlmPelanggan2("Jakarta Utara");
        pelanggan.setEmailPelanggan("thiago.alcântara@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Trent Alexander-Arnold"));
        pelanggan.setNmPelanggan("Trent Alexander-Arnold");
        pelanggan.setAlmPelanggan1("Perumahan Puri Kartika B-5 No. 14 Banjarsari");
        pelanggan.setAlmPelanggan2("cipocok jaya, kota Serang");
        pelanggan.setEmailPelanggan("trent.alexander-arnold@gmail.com");

        result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);


        Pelanggan[] pelanggans = pelangganRepository.getAll();
        assertTrue(pelanggans.length > 0);
    }

    @Test
    void testGetAllFailedNoDataFound() {
        pelangganRepository.removeAll();
        Pelanggan[] pelanggans = pelangganRepository.getAll();
        assertEquals(0, pelanggans.length);
    }

}


