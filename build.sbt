val scala3Version = "3.4.1"

val fernflowerVersion = "61974b28d5"
val fernflowerPackageName = "fernflower"

val decompile = taskKey[Unit]("decompile")

import scala.sys.process.Process
lazy val root = project
  .in(file("."))
  .settings(
    name := "decompile-recursion",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    (Test / scalaSource) := (Compile / scalaSource).value,

    resolvers += "jitpack" at "https://jitpack.io",

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest-diagrams" % "3.2.16",
      "org.scalatest" %% "scalatest-funspec" % "3.2.15",
      "com.github.fesh0r" % fernflowerPackageName % fernflowerVersion,
    ),

    decompile := {
      val jarList = (Compile / dependencyClasspathAsJars).value

      val maybeAttribute = jarList
        .find(_.data.getPath().contains(fernflowerPackageName))

      val maybeProcessBuilder = for {
        attribute <- maybeAttribute
        fernflowerJar = attribute.data.asPath
        inputDir = target.value / (s"scala-${scalaVersion.value}") / "classes"
        outputDir = baseDirectory.value / "decompiled"
        command = s"java -jar $fernflowerJar $inputDir $outputDir"
      } yield Process(command)

      maybeProcessBuilder match {
        case None => println("Error: Unable to find the required JAR file.")
        case Some(builder) => builder.!
      }
    }
  )
