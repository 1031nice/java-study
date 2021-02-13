import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.List;

public class FilePathStudy {

    public static void main(String[] args) throws IOException {

//        // create file (File)
//        File newFile = new File(parent.getParentFile().getParentFile().getParent() + "/test.txt");
//        if(newFile.createNewFile())
//            System.out.println("파일 생성 성공");
//        else
//            System.out.println("파일 생성 실패");
//        System.out.println(newFile);

        // create file

        // JAVA SE 9 Path API의 Files
//        List<String> lines = Files.readAllLines(file.toPath());
//        lines.forEach(System.out::println);

//        Path path = Paths.get("./");
//        path = path.toAbsolutePath();
//        Path newFile = Files.createFile(path, );
//        System.out.println(newFile.toString());

/*        // FileInputStream for raw byte
        FileInputStream inputStream = new FileInputStream(file);
        int read = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while((read = inputStream.read()) != -1){
            stringBuilder.append((char)read); // 한글은 안된다
        }
        System.out.println(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());*/

/*        // FileReader(extends InputStreamReader) for character
        FileReader fileReader = new FileReader(file);
        while((read = fileReader.read()) != -1){
            stringBuilder.append((char)read); // 한글 되네?
        }
        System.out.println(stringBuilder.toString());*/
    }

}
