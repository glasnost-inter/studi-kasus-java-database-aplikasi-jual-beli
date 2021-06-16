package repository;

import com.zaxxer.hikari.HikariDataSource;
import entity.Pelanggan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.CodeGenerator;
import util.DatabaseUtil;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PelangganRepositoryImplTest {
    private HikariDataSource dataSource;
    private PelangganRepository pelangganRepository;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();
        pelangganRepository = new PelangganRepositoryImpl(dataSource);
    }

    @Test
    void testAdd_success() {
        Pelanggan pelanggan = new Pelanggan();

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Sjafril Effendi"));
        pelanggan.setNmPelanggan("Sjafril Effendi");
        pelanggan.setAlmPelanggan1("Tanjung Priok");
        pelanggan.setAlmPelanggan2("Jakarta Utara");
        pelanggan.setEmailPelanggan("sjafril.effendi@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Ang Harry Tjahjono"));
        pelanggan.setNmPelanggan("Ang Harry Tjahjono");
        pelanggan.setAlmPelanggan1("Urip Sumoharjo");
        pelanggan.setAlmPelanggan2("Karampuang");
        pelanggan.setEmailPelanggan("ang.harry@gmail.com");

        result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

    }

    @Test
    void testAdd_failed() {
        Pelanggan pelanggan = new Pelanggan();

        var strUUID = UUID.randomUUID().toString();

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Patrick Cao"));
        pelanggan.setNmPelanggan("Patrick Cao");
        pelanggan.setAlmPelanggan1("Komplek Villa Pamulang");
        pelanggan.setAlmPelanggan2("Cluster Bellarosa");
        pelanggan.setEmailPelanggan("patrick.cao@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Andika Hirlanda Putra"));
        pelanggan.setNmPelanggan("Andika Hirlanda Putra");
        pelanggan.setAlmPelanggan1("Lion Parcel Mega");
        pelanggan.setAlmPelanggan2("Bekasi Hypermall");
        pelanggan.setEmailPelanggan("andika.hirlanda@gmail.com");

        result = pelangganRepository.add(pelanggan);
        assertEquals(-1,result);

    }

    @Test
    void testDelete_success() {

        Pelanggan pelanggan = new Pelanggan();

        var strUUID = UUID.randomUUID().toString();

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Majid Abbara"));
        pelanggan.setNmPelanggan("Majid Abbara");
        pelanggan.setAlmPelanggan1("Perum Klipang Blk");
        pelanggan.setAlmPelanggan2("Sendang Jaya");
        pelanggan.setEmailPelanggan("majdi.abbara@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        result = pelangganRepository.removeById(strUUID);
        assertEquals(1,result);
    }

    @Test
    void testDelete_failed() {

        Pelanggan pelanggan = new Pelanggan();

        var strUUID = UUID.randomUUID().toString();

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Majid Abbara"));
        pelanggan.setNmPelanggan("Majid Abbara");
        pelanggan.setAlmPelanggan1("Perum Klipang Blk");
        pelanggan.setAlmPelanggan2("Sendang Jaya");
        pelanggan.setEmailPelanggan("majdi.abbara@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        result = pelangganRepository.removeById(UUID.randomUUID().toString());

        assertEquals(0,result);
    }

    @Test
    void testUpdate_success() {

        Pelanggan pelanggan = new Pelanggan();

        var strUUID = UUID.randomUUID().toString();

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Mathew Sueoka"));
        pelanggan.setNmPelanggan("Mathew Sueoka");
        pelanggan.setAlmPelanggan1("Trio LT1");
        pelanggan.setAlmPelanggan2("Mampang Prapatan");
        pelanggan.setEmailPelanggan("mathew.sueoka@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Mathew Sueoka, Msc"));
        pelanggan.setNmPelanggan("Mathew Sueoka, Msc");
        pelanggan.setAlmPelanggan1("Komplek Triloka");
        pelanggan.setAlmPelanggan2("Pancoran Barat");
        pelanggan.setEmailPelanggan("mathew.sueoka.leon@gmail.com");


        result = pelangganRepository.updateById(pelanggan);
        assertEquals(1,result);
    }

    @Test
    void testUpdate_failed() {

        Pelanggan pelanggan = new Pelanggan();

        var strUUID = UUID.randomUUID().toString();

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Rupsa Pandit"));
        pelanggan.setNmPelanggan("Rupsa Pandit");
        pelanggan.setAlmPelanggan1("D.I Panjaitan");
        pelanggan.setAlmPelanggan2("Rawa Bunga");
        pelanggan.setEmailPelanggan("rupsa.pandit@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Rupsa Pandit, Phd"));
        pelanggan.setNmPelanggan("Rupsa Pandit, Phd");
        pelanggan.setAlmPelanggan1("Berawa Beach");
        pelanggan.setAlmPelanggan2("Tibu Beneng");
        pelanggan.setEmailPelanggan("rupsa.pandit@gmail.com");


        result = pelangganRepository.updateById(pelanggan);
        assertEquals(0,result);
    }

    @Test
    void testGetById_success() {

        Pelanggan pelanggan = new Pelanggan();

        var strUUID = UUID.randomUUID().toString();

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Harshita Singh"));
        pelanggan.setNmPelanggan("Harshita Singh");
        pelanggan.setAlmPelanggan1("Jend A Yani");
        pelanggan.setAlmPelanggan2("Teuku Umar");
        pelanggan.setEmailPelanggan("harshita.singh@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Melisha Siska Juminto"));
        pelanggan.setNmPelanggan("Melisha Siska Juminto");
        pelanggan.setAlmPelanggan1("Tasik Indah Plaza");
        pelanggan.setAlmPelanggan2("Ki Ageng Gribig");
        pelanggan.setEmailPelanggan("melisha.siska.juminto@gmail.com");

        result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);


        Pelanggan[] pelanggans = pelangganRepository.getById(strUUID);
        assertEquals(1,pelanggans.length);
    }

    @Test
    void testGetAll_success() {

        Pelanggan pelanggan = new Pelanggan();

        var strUUID = UUID.randomUUID().toString();

        pelanggan.setId(strUUID);
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Feri Aris Rinaldi"));
        pelanggan.setNmPelanggan("Feri Aris Rinaldi");
        pelanggan.setAlmPelanggan1("May Ruslan III");
        pelanggan.setAlmPelanggan2("Gatot Subroto");
        pelanggan.setEmailPelanggan("feri.aris.rinaldi@gmail.com");

        var result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);

        pelanggan.setId(UUID.randomUUID().toString());
        pelanggan.setKdPelanggan(CodeGenerator.input("PLG","Wisudo Harsanto"));
        pelanggan.setNmPelanggan("Wisudo Harsanto");
        pelanggan.setAlmPelanggan1("Arif Margono");
        pelanggan.setAlmPelanggan2("Raya Mandala 14");
        pelanggan.setEmailPelanggan("wisudo.harsanto@gmail.com");

        result = pelangganRepository.add(pelanggan);
        assertEquals(1,result);


        Pelanggan[] pelanggans = pelangganRepository.getById(strUUID);
        assertTrue(pelanggans.length > 0);
    }

}
