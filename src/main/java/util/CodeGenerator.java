package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CodeGenerator {

    public static String input(String prefix,String name){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss");
        StringBuilder codeEntity = new StringBuilder(100);
        String[] kata = name.split(" ");
        List<String> al;
        al = Arrays.asList(kata);

        codeEntity.append(prefix.concat("###".substring(0,3-prefix.length())));
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

        return codeEntity.toString().substring(0,23).toUpperCase();
    }
}
