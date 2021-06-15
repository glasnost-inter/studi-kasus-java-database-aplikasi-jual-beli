package util;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CodeGeneratorTest {


    @Test
    void postFix() {

        /*
        String s = "salm";

        if(s.length()<8){
            System.out.println(s.concat("00000000".substring(0,8-s.length())));
        }else {
            System.out.println(s.substring(0,8));
        }
        */

        /*
        String s1 = "s";

        if(s1.length()<4){
            System.out.println(s1.concat("0000".substring(0,4-s1.length())));
        }else {
            System.out.println(s1.substring(0,4));
        }
        */

        /*
        String s2 = "s1";

        if(s2.length()<3){
            System.out.println(s2.concat("000".substring(0,3-s2.length())));
        }else {
            System.out.println(s2.substring(0,3));
        }

         */

        String s3 = "1";

        if(s3.length()<2){
            System.out.println(s3.concat("0"));
        }else {
            System.out.println(s3.substring(0,2));
        }
    }


    @Test
    void postFixKata() {
        String name = "Salman Farid Bin Abubakar Sulaiman";
        String prefix = "PLG";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss");
        StringBuilder codeEntity = new StringBuilder(100);
        String[] kata = name.split(" ");
        List<String> al;
        al = Arrays.asList(kata);

        //System.out.println("Size "+al.size());
        codeEntity.append(prefix);
        codeEntity.append(formatter.format(date));
        if (al.size() == 1){
            for(String s: al){
                if(s.length()<8){
                    codeEntity.append(s);
                    codeEntity.append("########".substring(0,8-s.length()));
                }else {
                    codeEntity.append(s.substring(0, 8));
                }
            }
        }
        else if (al.size() == 2){
            for(String s: al){
                if(s.length()<4){
                    codeEntity.append(s.concat("####".substring(0,4-s.length())));
                }else {
                    codeEntity.append(s.substring(0, 4));
                }
            }
        }
        else if (al.size() == 3){
            for(String s: al){
                if(s.length()<3){
                    codeEntity.append(s.concat("###".substring(0,3-s.length())));
                }else {
                    codeEntity.append(s.substring(0, 3));
                }
            }
        }
        else if (al.size() == 4){
            for(String s: al){
                if(s.length()<2){
                    codeEntity.append(s.concat("#"));
                }else {
                    codeEntity.append(s.substring(0, 2));
                }
            }
        }
        else if (al.size() > 4) {
            for(String s: al){
                if(s.length()<2){
                    codeEntity.append(s.concat("#"));
                }else {
                    codeEntity.append(s.substring(0, 2));
                }
            }
        }
        System.out.println(codeEntity.toString().substring(0,23).toUpperCase());
    }

    @Test
    void satuKata() {

        var kode = CodeGenerator.input("TSX","test");

        System.out.println(kode);
    }

    @Test
    void duaKata() {

        var kode = CodeGenerator.input("PLG","st aja");

        System.out.println(kode);
    }

    @Test
    void tigaKata() {

        var kode = CodeGenerator.input("CAX","DA UI LLLLLS");

        System.out.println(kode);
    }


    @Test
    void empatKata() {

        var kode = CodeGenerator.input("CAX","SUSU KENTAL MANIS CAP");

        System.out.println(kode);
    }


    @Test
    void limaKata() {

        var kode = CodeGenerator.input("GGF","SUSU KENTAL MANIS CAP NONA");

        System.out.println(kode);
    }
}
