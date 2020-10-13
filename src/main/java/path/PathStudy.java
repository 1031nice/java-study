package path;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathStudy {

    public static void main(String[] args) throws IOException {
//        Path path = Paths.get("./input.txt"); // . = 현재 폴더 = 프로젝트 폴더
        File file = new File("./src/main/java/test.txt"); // 이렇게 적어줘야 찾음
        FileInputStream inputStream = new FileInputStream(file);
        int read = 0;
        byte [] bytes = new byte[2];
        while((inputStream.read(bytes)) != -1){
            System.out.println(new String(bytes, "UTF-8"));
        }
        // inputstreamreader로 라인단위로 읽는것도 해봐야하고
        ;
    }

}
