import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class FilePathStudyTest {

    File file;

    @Before
    public void before(){
        file = new File("./src/main/java/test.txt"); // 이렇게 적어줘야 찾음
    }

    @Test
    public void listFiles(){
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
        Optional<File> any2 = Arrays.stream(fakeParent.listFiles()).filter(c -> {
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
    public void list() {
        File dir = new File("./src/main/java");
        if(dir.isDirectory()) { // File이 directory가 아닐 경우 null을 리턴
            Arrays.stream(dir.list()).forEach(System.out::println);
        }
    }

    @Test
    public void mkdir() {
        File path = new File("./src/main/java/helloWorld");
        assertThat(path.mkdir()).isTrue();
        assertThat(path.exists()).isTrue();
        assertThat(path.isDirectory()).isTrue();
    }

    @Test
    public void mkdirs() {
        File path = new File("./src/main/java/hello/world");
        assertThat(path.mkdir()).isFalse(); // 중첩 dir을 만들 때는 mkdir을 쓸 수 없다
        assertThat(path.mkdirs()).isTrue();
        assertThat(path.exists()).isTrue();
        assertThat(path.isDirectory()).isTrue();
    }

    @Test
    public void fileCreateFile() throws IOException {
        File newFile = new File("./src/main/java/hollymollylilly.txt");
        assertThat(newFile.createNewFile()).isTrue();
        File newFile2 = new File("./src/main/java/holly/molly/lilly.txt"); // 없는 경로에 파일 만들려고하면 exception
//        assertThat(newFile2.createNewFile()).isFalse(); // exception
        assertThat(newFile2.exists()).isFalse(); // 꼭 존재해야만 File 객체로 만들 수 있는 것은 아니다
        assertThat(newFile2.getParentFile().mkdirs()).isTrue();
        assertThat(newFile2.createNewFile()).isTrue();
    }

    @Test
    public void fileSystem() {
        Path path = Paths.get("./src/main/java");
        FileSystem fileSystem = path.getFileSystem();
        System.out.println("separator: " + fileSystem.getSeparator());

        Iterable<FileStore> fileStores = fileSystem.getFileStores();
        fileStores.forEach(System.out::println);

        Iterable<Path> rootDirectories = fileSystem.getRootDirectories();
        rootDirectories.forEach(System.out::println);
    }

    // path 속 element의 수(루트 제외, 존재하느냐와 관계 없음, 상대경로이면 상대경로속 개수, 절대경로면 절대경로속 개수)
    @Test
    public void getNameCount(){
        Path path = Paths.get("./1/2/3/4/5/6/7/8/9");
        assertThat(path.getNameCount()).isEqualTo(10);
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        System.out.println(absolutePath.getNameCount());

        Iterable<Path> rootDirectories = path.getFileSystem().getRootDirectories();
        rootDirectories.forEach(System.out::println);
        assertThat(rootDirectories.iterator().next().getNameCount()).isEqualTo(0); // 루트는 경로 0
    }

    // path의 원소를 index로 얻기(타입은 Path인데 경로는 앞뒤로 다 잘림.. 무슨의미지 그럼?)
    @Test
    public void getName() throws IOException {
        Path path = Paths.get("./src/main/java");
        System.out.println(path.toAbsolutePath().getName(3));
        Path what = path.toAbsolutePath().getName(3);
        Path the = Paths.get("./");
        System.out.println(the.toRealPath());
        assertThat(what.compareTo(the.toRealPath())).isNotEqualTo(0); // getName으로 얻은 경로는 원래 경로와 다르다

        // getName의 시작은 루트 바로 밑 폴더(ex C:/Users/... 이런식이면 getName(0)는 Users)
        Path root = path.getFileSystem().getRootDirectories().iterator().next();
        assertThat(root.compareTo(path.toAbsolutePath().getRoot())); // getRoot()는 절대경로에만 있나보다
        assertThat(Arrays.stream(root.toFile().listFiles()).filter(c->{
            System.out.println(c);
            if(c.toPath().toString().contains(path.toAbsolutePath().getName(0).toString()))
                return true;
            else
                return false;
        }).findAny()).isNotEmpty();
    }

    @Test
    public void absolutePathVsRelativePath(){
        Path path = Paths.get("./src/main/java");
        Path absolutePath = path.toAbsolutePath();
        assertThat(path.compareTo(absolutePath)).isNotEqualTo(0);
    }



}