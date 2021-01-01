### 목차
- [package](#package)
    - [namespace](#namespace)
    - [fully qualified name](#fully-qualified-name)
    - [default package](#default-package)
    - [import](#import)
- [claspath](#classpath)
- [access modifier](#access-modifier)

### package
패키지는 클래스와 인터페이스 그리고 다른 레퍼런스 타입의 집합이다.
패키지는 관련있는 클래스들을 그룹으로 묶으며, 자신이 포함하고 있는 특정 클래스를 위한 네임스페이스를 정의한다.

#### namespace
네임스페이스란 이름들의 집합을 다른 이름들의 집합과 구분하기 위해 고안된 것으로
이름들끼리 충돌이 나지 않도록 구분짓는 논리적인 컨테이너라고 생각할 수 있다.
즉, 패키지가 클래스를 위한 네임스페이스를 정의한다는 뜻은
패키지가 클래스들의 이름이 충돌되지 않도록, 서로 구별되도록 논리적인 이름 컨테이너 역할을 한다는 뜻이다.
패키지 덕분에 이름이 같은 클래스가 여러 개라고 하더라도 모호함 없이 구분해낼 수 있다. 

#### fully qualified name
모든 클래스는 두 개의 이름을 갖는다. 하나는 클래스를 정의할 때 붙은 이름이고,
다른 하나는 자신을 포함하고 있는 패키지의 이름까지 포함한 이름(fully qualified name)이다.
예를 들어, String 클래스의 이름은 String이지만 이는 java.lang 패키지에 속해있으므로
fully qualified name은 java.lang.String이다.

#### `package`
클래스를 어떤 패키지에 담고 싶은지 명시하기 위해서는 `package` 키워드를 사용해야 한다.
이는 반드시 자바 코드의 가장 처음에 위치해야 하며 `package` 키워드 뒤에
원하는 패키지 이름과 세미콜론을 붙여주어야 한다.

`package org.apache.commons.net;`

위와 같은 패키지 directive가 있는 파일에서 정의된 모든 클래스는 org.apache.commons.net에 속하게 된다.

#### default package
만약 자바 파일에 패키지 directive가 없는 경우 해당 파일에서 정의된 모든 클래스는 이름이 없는 디폴트 패키지에 속하게 된다.
이 경우 qualified name과 unqualified name이 동일하다.

패키지의 가장 중요한 역할 중 하나는 자바 네임스페이스를 구분함으로써 클래스 간의 이름 충돌을 방지하는 것이다.
패키지 이름이 java.util.List와 java.awt.List 같이 이름이 같은 클래스를 구분짓는 유일한 요소다.
패키지 이름이 이런 역할을 하기 위해서는 패키지 이름들은 반드시 구분 가능해야 한다.

#### import
자바 코드에서 클래스나 인터페이스를 사용할 때 기본적으로는 반드시 패키지 이름을 포함하는
타입의 fully qualified 이름을 사용해야 하나 여기에는 세 가지 예외가 있다.

1. java.lang 패키지에 있는 타입들은 너무 중요하고 매우 흔하게 사용되어 패키지 이름 없이
단순히 클래스 이름만으로 참조할 수 있다. (automatic imports)
2. 현재 타입(클래스, 인터페이스 등)의 코드에서는 현재 타입이 속한 패키지의 다른 타입을 패키지 이름 없이
클래스 이름만으로 참조할 수 있다. 즉, p.T 타입의 코드에서 패키지 p에 있는 다른 타입들은
앞에 p를 명시하지 않고도 그냥 그 타입의 이름만 이용해서 참조할 수 있다. (automatic imports)
3. `import` 키워드를 통해 해당 네임스페이스로 imported된 타입들 역시 패키지 이름 없이
그들의 이름만으로 참조할 수 있다.

`import` 선언부는 `package` 선언부 바로 다음에 위치해야 한다.

`import` 선언부는 두 가지 형태를 갖는다.
1. single type import: 하나의 타입을 네임스페이스로 import
<br>`import java.io.File;`
2. on-demand type import: 패키지에 있는 모든 클래스를 네임스페이스로 import
<br> `import java.io.*; // java.io 패키지의 모든 클래스를 패키지 이름 없이 참조 가능`
<br> 참고 1. 서브패키지까지는 네임스페이스로 import되지 않는다. 즉, java.util 패키지를
import 했어도, java.util.zip.ZipInputStream 클래스를 사용할 때는
패키지 이름을 포함한 fully qualifed name을 사용해야 한다.
<br> 참고 2. 패키지의 모든 클래스에 대해 single type import로 하나하나 import한 것과
on-demand type import한 것은 다르다. 엄밀히 얘기해서 on-demand type import는 해당 패키지의 모든 클래스를
패키지 이름 없이 바로 참조할 수 있다는 것일 뿐 실제로 import 하는 것은 이름에서 알 수 있듯 코드에서 쓴 클래스이다.
 
### classpath
자바 파일은 클래스 파일로 컴파일된다. JVM은 클래스 파일을 읽고 명령을 수행한다.
클래스패스란 JVM이 클래스 파일을 찾는 위치이다.
현재 디렉토리에서 시작하여 해당 클래스의 fully qualified name에 해당하는 폴더를 따라 해당 클래스를 찾는 것이 디폴트이다.
예를 들어, com.davidflanagan.examples.Point 클래스라면 현재 폴더에서
com/davidflanagan/examples/의 경로를 따라 Point.class 파일을 찾는다.
클래스 파일을 현재 폴더가 아닌 다른 곳에서 찾게 만들고 싶다면 두 가지 방법이 있다.

1. 인터프리터를 호출할 때 -classpath 옵션을 준다.  
`(1)java (2)-classpath (3)/opt/Jude (4)com.davidflanagan.jude.Jude`
<br> (1) JVM을 구동시키는 실행 파일
<br> (2) 클래스 파일위치를 명시할 때 쓰는 옵션
<br> (3) 클래스 파일의 위치
<br> (4) 실행시킬(main 메소드를 가진) 클래스의 fully qualified name

2. CLASSPATH 환경변수를 설정한다.
<br> 클래스패스란 클래스 파일이 위치한 경로라고 한다. 그런데 여태까지 설정한 CLASSPATH는 jdk 폴더의 bin 폴더였다.
하지만 bin 폴더는 클래스 파일이 저장되는 위치가 아니라 javac나 java 같은 실행 파일이 있는 위치였다.
추측컨대 자바 입문자가 학습겸 자바 프로그램을 윈도우 명령 프롬프트에서 실행할 때
어떤 위치에서든 java 실행 파일을 실행할 수 있도록 CLASSPATH를 bin 폴더로 설정했던 것이고,
원래의 쓰임은 클래스 파일의 위치를 설정하는 것이 맞는 것이 아닌가 싶다.
대부분은 IDE에서 자바 프로그램을 실행하니 CLASSPATH를 굳이 제대로 설정하지 않았던 것이 아닌가 싶다. 

### access modifier

* `private`: 클래스 자기 자신 안에서만 접근 가능
* package-private(no modifier): 같은 패키지에서만 접근 가능
* `protected`: 다른 패키지인 경우라도 자식 클래스라면 접근 가능
<br>(같은 패키지일 경우 부모 자식 관계가 아니어도 접근 가능)
* `public`: 어디서나 접근 가능

엄격한 정도는 `private`, package-private, `protected`, `public` 순이다.
`protected`가 가장 헷갈리기 쉬운 것 같다. 오직 자식 클래스에서만 접근할 수 있다는 뜻이 아니라
다른 패키지 속 클래스라도 자식 클래스라면 접근할 수 있다는 뜻이다. (같은 패키지라면 자식 클래스가 아니더라도 접근할 수 있다.)
즉, `protected`는 부모 자식 관계로 '제한'을 두는 것이라기 보다는
부모 자식 관계는 추가적으로 접근을 '허용'해주는 것으로 보는 것이 맞는 것 같다.
다시 말해, access modifier를 붙이지 않는 것이(package-private) `protected`를 붙이는 것보다 더 엄격한 제한이며
`protected`는 package-private에 접근을 허용하는 경우를 추가한 것이다.  

참고: Java in a Nutshell 7th