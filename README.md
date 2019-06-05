# Scala Build Tool - Getting Started

## Module 02: Introduction to SBT

### How to install JDK 8?
#### Follow the Link
[Link](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

### Verify the Java 8 is installed
```sh
java -version
```

### How to install sbt?
#### Mac
[Homebrew](https://brew.sh/)  

##### Verify if Homebrew is installed
```sh
brew --version
```

#### Install sbt using Homebrew
```sh
brew install sbt
```

##### Verify if sbt is installed
```sh
sbt sbtVersion
```

#### Windows
[Link](https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Windows.html)  

#### Linux
[Ubuntu and other Debian-based distributions](https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Linux.html#Ubuntu+and+other+Debian-based+distributions)    
[Red Hat Enterprise Linux and other RPM-based distributions](https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Linux.html#Red+Hat+Enterprise+Linux+and+other+RPM-based+distributions)  
[Gentoo](https://www.scala-sbt.org/1.x/docs/Installing-sbt-on-Linux.html#Gentoo)  

### How to install IntelliJ IDEA Community Edition?  
[Link](https://www.jetbrains.com/idea/download)  



## Module 03: SBT Fundamentals
### 02: Working with sbt shell
```sh
mkdir sbt101
cd sbt101

touch build.sbt
ll
sbt
```

```sbtshell
scalaVersion
help
help compile
help clean
help test
```

```sh
cd sbt101
mkdir -p src/main/directory
```

```scala
// vi src/main/scala/HelloWorld.scala
object HelloWorld extends App {
 println("Hello sbt!")
}
```

```sbtshell
compile
run
```

```scala
// vi src/main/scala/Echo.scala
object Echo extends App {
  println(s"You said: ${if (args.isEmpty) "nothing" else args.mkString(" ")}")
}
```

```sbtshell
run A B C
clean
run
;clean ;run "aha! now I know"
!
clean
!!
help console
console
```

```scala
Echo.main(Array("I run from scala console as well"))
:q
```

```sbtshell
exit
```

```sh
sbt clean
sbt clean compile
sbt "run A B C" 
```

### 03: Understanding the sbt directory structure
```sbtshell
sourceDirectories
baseDirectory
scalaSource
target
source
```

### 04: Understanding Build Definition and Settings
```sh
// vi build.sbt
name := "sbt201"
```

```sbtshell
name
help reload
reload
inspect name
reload
```

```sh
// vi build.sbt, change name
name := "sbt201"
```

```sbtshell
reload
inspect package
inspect clean
inspect run
settings
settings -V
tasks
tasks -V
```

### 05: Creating your own settings
```sh
// vi build.sbt
lazy val emotion = settingKey[String]("How are you feeling")
emotion := "Fantastic!"
```

```sbtshell
reload
help emotion
inspect emotion
emotion
```

```sh
// vi build.sbt
val randomInt = taskKey[Int]("Give me random number")
randomInt := scala.util.Random.nextInt
```

```sbtshell
reload
help randomInt
randomInt
show randomInt
```

### 06: Understanding task graph in build definition
```sh
// vi build.sbt
val emotion = settingKey[String]("How are you feeling")
emotion := "Fantastic!"

val status = settingKey[String]("What is your current status?")
status := {
  val e = emotion.value
  s"Grateful and $e"
}
```

```sbtshell
reload
status
```

```sh
// vi build.sbt
val emotion = settingKey[String]("How are you feeling")
emotion := "Fantastic!"


val randomInt = taskKey[Int]("Give me random number")
randomInt := {
  println(emotion.value)
  scala.util.Random.nextInt
}
```

```sbtshell
reload
show randomInt
```

```sh
// vi build.sbt
val randomInt = taskKey[Int]("Give me random number")
randomInt := {
  scala.util.Random.nextInt
}

val status = settingKey[String]("What is your current status?")
status := {
  val r = randomInt.value
  s"Grateful and $r"
}
```

```sbtshell
reload
```

## Module 04: Project Lifecycle using SBT
### 03: Creating the project base
```sbtshell
name
```

### 04: Creating the first calculator
```sbtshell
;reload ;compile
projects
calculators/compile
calculators/clean
project calculators
;clean ;compile
projects
reload
projects
;clean ;compile
;reload
projects
;clean ;compile
;clean ;run
;clean ;calculators/run 100 20
```

### 05: Understanding Scopes and Executing Test
```sbtshell
test
calculators/run
calculators/Test/run
```

### 06: Adding another Calculator
```sbtshell
compile
calculators/runMain CompoundInterest 5000 5 10
```

#### References
[Compound Interest Calculator](https://www.ajdesigner.com/phpinterest/interest_regular_deposits_p.php#ajscroll)  

## 05: Refactoring project for bigger changes
### 02: Adding external libraries in sbt
```sbtshell
unmanagedBase
```

#### References
[requests-scala](https://github.com/lihaoyi/requests-scala)  
[scala-xml](https://github.com/scala/scala-xml)  

### 03: Working with 3rd party libraries
```sbtshell
api/console
```

```scala
val r = requests.get("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml")
r.statusCode
r.headers
r.headers("content-type")
import scala.xml._  // (that's why we needed scala-xml)
r.text
val xmlResponse = XML.loadString(r.text)
val currencyCodes: Seq[String] = (xmlResponse \\ "@currency").map(node => node.text)
// Explain how to fetch attributes in xml in scala, refer to medium article
val euroToCurrencyMultipliers: Seq[Double] = (xmlResponse \\ "@rate").map(node => node.text.toDouble)
val currencyCodeMultipliers = (currencyCodes zip euroToCurrencyMultipliers).toMap
```

#### References
[European Central Bank Currency API](https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml)  
[Working with XML in Scala](https://medium.com/@harittweets/working-with-xml-in-scala-bd6271a1e178)  

### 04: Execute Tests using ScalaTest
```sbtshell
test
Test/parallelExecution
~test
```

#### References
[ScalaTest](http://www.scalatest.org/)  

### 06: Adding dependencies between sub-projects
```sbtshell
calculators/runMain NetWorthMultiCurrency "100000 EUR,9000 USD" "59100 EUR,12200 USD"
```

## 06: Open-sourcing the project
### 02: Packaging the artifacts
```sbtshell
;clean ;stage
```

```sh
ls calculators/target/scala-2.12/
ls api/target/scala-2.12/

calculators/target/universal/stage/bin/net-worth 100 20
calculators/target/universal/stage/bin/compound-interest 5000 5 10
api/target/universal/stage/bin/api
```

```sh
docker -v
```

```sbtshell
docker:publishLocal
```

```sh
docker images
docker run -it calculators:0.1.0-SNAPSHOT 100 20
```

#### References
[sbt-native-packager](https://sbt-native-packager.readthedocs.io/en/latest/)  

#### References
[Docker Desktop](https://www.docker.com/products/docker-desktop)  
[Travis-CI Scala](https://docs.travis-ci.com/user/languages/scala/#projects-using-sbt)  
[Bintray: Open-source your project](https://bintray.com/)  
[Heroku CLI tools](https://devcenter.heroku.com/articles/heroku-cli#download-and-install)  
[Heroku Procfile](https://devcenter.heroku.com/articles/procfile)  

---
# Todo:
- Add bintray link for the package