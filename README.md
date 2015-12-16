# 코드 작성 규칙 #

* 하나의 Scope는 Tab으로 구분.
* Scope의 시작 { 는 이전 라인의 끝에 붙임.


```
#!java
class Sample{
	//상수 선언 - 모두 대문자로 단어마다 구분은 _ 를 사용.
    public final static TICKET_ONEWAY = 0;
    public final static TICKET_TWOWAY = 1;

    //private field - 시작은 소문자로 Camel표기법을 사용. 왠만하면 클래스의 첫 글자를 소문자로한 이름으로 선언.
    private FlightData flightData;
    //list 형태나 기타 Collection 형태는 아래와 같이 선언.
    private List<FlightData> filghtDataList;

    //public field - public field는 상수를 제외하고 선언할일이 없도록 하는 것이 좋음.

    //constructor
    public Sample(){}
    
    //생성자에서 default값 할당시 아래와 같이 정의.
    public Sample(FlightData flightData){
    	this.flightData = flightData;
    }

    //method
    public int sum(int n){
		int sum;

		for(int i=0; i<n; i++){
		    sum += i;
		}

		return sum;
    }
}

```

#1. 클래스 명은 반드시 대문자로 시작한다.#
예) Myclass(O), myclass(X), myClass(X)
 
#2. 패키지를 표현하는 이름은 모두 소문자를 사용한다. #

```
#!java

mypackage, com.company.application.ui 
```

 
패키지 이름의 시작부는 반드시 도메인 이름으로 소문자를 사용해야만 합니다. 
충돌 방지를 위해 패키지명은 보통 도메인의 역순으로 한다.
도메인이 www.myhouse.com 이라면
패키지는 com.myhouse 정도로 하면 됩니다.
 
#3. 변수의 이름은 반드시 소문자로 시작하고 카멜(낙타)명명법을 이용한다.(명사) #
account, eventHandler  
예) Account account; // 선언문
변수와 메소드는 카멜 명명법을 쓴다. 카멜=낙타처럼 중간에 혹이 볼록(凸), 2단어 연결시 중간에 단어 앞글자만 대문자로 작성.
 
#4. 일반적인 변수의 이름은 타입의 이름과 동일하게 지정한다. #

```
#!java

void setTopic (Topic topic)      // void setTopic (Topic value) 이 아님
                                 // void setTopic (Topic aTopic) 이 아님
                                 // void setTopic (Topic x) 이 아님
void connect (Database database) // void connect (Database db) 가 아님
                                 // void connect (Database oracleDB) 가 아님  
```

용어나 이름의 수를 줄이는 것이 코드의 복잡도를 줄여줍니다. 또한 변수의 이름만으로도 그 타입을 손쉽게 유추할 수 있게 해준다는 장점도 있습니다. 만약 어떠한 이유에선가 이러한 관례가 맞지 않는 것처럼 느끼신다면, 이는 분명 타입이름 자체를 잘못 지정한 것입니다. 일반적이지 않은 변수들은 각기 나름의 역할(role)을 가지고 있습니다. 이러한 변수들은 역할과 변수의 타입을 결함하여 이름을 짓곤 합니다. 


```
#!java

Point startingPoint, centerPoint;
Name  loginName;
```

 
#5. 넓은 범위에 영향을 미치는 변수는 긴 이름을 부여하고, 좁은 범위의 변수는 짧은 이름을 부여한다. #
　 
임시 저장공간이나 인덱스로 사용되는 Scratch variable (주: 의미있는 값을 갖지 않고 그때그때 상황에 따라 값들을 잠시 보관해 두기 위한 변수로, 대개 보유한 값이 얼마 후에 의미가 없어지거나 삭제됨)들은 매우 짧은 이름을 부여하십시요. 프로그래머가 그러한 변수들을 읽음과 동시에, 이 변수는 몇 라인 뒤에 그 값이 유효하지 않을 것임을 짐장할 수 있게 해야 합니다.
보편적인 scratch variable 로는 정수를 저장하는 i, j, k, m, n 가 있고 문자를 저장하는 c, d 가 있습니다.
 
 
#6. 메소드의 이름은 반드시 소문자로 시작하고 카멜(낙타)명명법을 이용한다.(동사) #

```
#!java

getName(), computeTotalWidth()  
```

이 방식은 변수의 이름을 지을 때 사용하는 관례와 동일 하지만, 메소드와 변수를 구분을 위해, 변수는 명사단어로, 메소드는 동사 단어로
 
 
 
#7. 타입을 표현하는 이름은 대소문자를 혼용할 수 있지만, 반드시 명사를 사용하고 시작 글자를 대문자로 지정한다. #

```
#!java

Account, EventHandler  
```


 
#8. 상수(final 변수)를 표현하는 이름은 반드시 모두 대문자로 지정하되 '_' 를 사용하여 단어들을 구분한다. #

```
#!java

MAX_ITERATIONS, COLOR_RED  
```

Java 개발자 커뮤니티에서 사용하는 일반적인 관습이며, Sun 社 가 Java 핵심 패키지에 사용하고 있는 것입니다. 일반적으로, 이러한 상수의 사용은 최소화해야만 합니다. 대다수의 경우 상수 변수를 메소드로 구현하는 것이 더 낫습니다: 

```
#!java

int getMaxIterations()     // NOT: MAX_ITERATIONS = 25 
{
  return 25;
} 
```

이러한 양식이 보다 읽기에 편하며, 클래스의 값을 참조하는 일관된 인터페이스를 제공할 수 있다는 장점을 가집니다. 
　

#9. 축약형(Abbreviation) 과 두문자어형(頭文字語: Acronym) 을 이름에 사용할 경우에는 전부 대문자로 지정하지 않는다. #

```
#!java

exportHtmlSource();    // exportHTMLSource(); 가 아님 
openDvdPlayer();       // openDVDPlayer(); 가 아님 
```

축약형 혹은 두문자어형 이름을 모두 대문자로 지정하게 되면 앞서 기술한 명명 지침들과 충돌이 발생하게 됩니다 (대표적으로 상수에 대한 명명 지침과 혼동될 수 있습니다). 그렇다고 이 유형의 이름을 dVD, hTML 등 과 같이 다양한 형태로 지정할 경우에는 가독성이 떨어집니다. 또 다른 문제점으로는 위의 예제에서 살펴볼 수 있는 바와 같이, 이 유형의 이름이 다른 이름과 결합되는 경우 가독성이 극도로 나빠진다는 것입니다. 후속하는 단어가 있을 경우는 더욱 각별히 주의해야 합니다. 
