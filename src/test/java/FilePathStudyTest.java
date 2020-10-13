import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FilePathStudyTest {

    File file;

    @Before
    public void before(){
        file = new File("./src/main/java/test.txt"); // 이렇게 적어줘야 찾음
    }

    @Test
    public void listFiles(){
        // 이상하네 여기서 listFiles는 바로 하위에 있는 것만 잘 가져오는데 왜 parent에서는 모든 하위의 것들 다 가져오지?
        File main = file.getParentFile().getParentFile();
        File[] files = main.listFiles();
        Arrays.stream(files).forEach(System.out::println);
        assertThat(files.length).isEqualTo(2);
    }

    @Test
    public void parent(){
        String absolutePath = file.getAbsolutePath();
        File parent = new File(absolutePath).getParentFile();
        Optional<File> any = Arrays.stream(parent.listFiles()).filter(c -> {
            try {
                if (Files.isSameFile(c.toPath(), file.toPath()))
                    return true;
                else
                    return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }).findAny();
        assertThat(any).isNotEmpty();

        File fakeParent = new File("./src/main");
        Optional<File> any2 = Arrays.stream(parent.listFiles()).filter(c -> {
            System.out.println(c);
            try {
                if (Files.isSameFile(c.toPath(), file.toPath()))
                    return true;
                else
                    return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }).findAny();
        assertThat(any2).isEmpty();
    }

    @Test
    public void fileCreateFile(){

    }

    @Test
    public void pathCreateFile(){

    }


}