# Scala Build Tool - Getting Started

## Module 02: Introduction to SBT

### How to install JDK 8?
#### Follow the Link
[Link](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

### Verify the Java 8 is installed
```sh
~> java -version
openjdk version "1.8.0_202"
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_202-b08)
OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.202-b08, mixed mode)
~>
```

### How to install sbt?
#### Mac
[Homebrew](https://brew.sh/)
##### Verify if Homebrew is installed
```sh
~> brew --version
Homebrew 2.0.5
Homebrew/homebrew-core (git revision 7f58; last commit 2019-03-19)
Homebrew/homebrew-cask (git revision c9d97; last commit 2019-03-19)
~>
```
#### Install sbt using Homebrew
```sh
brew install sbt
```
##### Verify if sbt is installed
```sh
~> sbt sbtVersion
[info] Loading settings for project global-plugins from idea.sbt ...
[info] Loading global plugins from /Users/hhimanshu/.sbt/1.0/plugins
[info] Loading project definition from /Users/hhimanshu/project
[info] Set current project to hhimanshu (in build file:/Users/hhimanshu/)
[info] 1.2.8
~>
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

## Calculators
### Compound Interest Calculator
https://www.ajdesigner.com/phpinterest/interest_regular_deposits_p.php#ajscroll

## 3rd Party Dependencies
[requests-scala](https://github.com/lihaoyi/requests-scala)


## References
[European Central Bank Currency API](https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml)
[Working with XML in Scala](https://medium.com/@harittweets/working-with-xml-in-scala-bd6271a1e178)
[Docker Desktop](https://www.docker.com/products/docker-desktop)
[Travis-CI Scala](https://docs.travis-ci.com/user/languages/scala/#projects-using-sbt)
[Bintray: Open-source your project](https://bintray.com/)
[Heroku CLI tools](https://devcenter.heroku.com/articles/heroku-cli#download-and-install)
[Heroku Procfile](https://devcenter.heroku.com/articles/procfile)

---
# ToDO:
- Add bintray link for the package