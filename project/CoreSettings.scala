import com.typesafe.sbt.SbtPgp.autoImportImpl._
import sbt.Keys._
import sbt._
import sbtassembly.AssemblyKeys._

object CoreSettings {

  val currentScalaVersion = "2.10.6"
  val organizationName = "com.recipegrace"
  val jenaVersion="3.0.0"
  val neo4jVersion="3.0.4"
  val username = System.getenv().get("SONATYPE_USERNAME")
  val password = System.getenv().get("SONATYPE_PASSWORD")
  val passphrase = System.getenv().get("PGP_PASSPHRASE") match {
      case x:String => x
      case null => ""
      }


  val coreSettings = Seq(
    name:="RDF2Neo4J",
    pgpPassphrase := Some( passphrase.toCharArray),
    pgpSecretRing := file("local.secring.gpg"),
    pgpPublicRing := file("local.pubring.gpg"),
    crossScalaVersions := Seq("2.10.6", "2.11.5"),
    organization := organizationName,
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.2.5",
      "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
      "org.apache.commons" % "commons-lang3" % "3.4",
      "commons-io" % "commons-io" % "2.4",
      "org.slf4j" % "slf4j-log4j12" % "1.7.10" % "test"
    ),
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value) Some(Resolvers.ossSnapshots)
      else Some(Resolvers.ossStaging)
    },
    credentials += Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", username, password),
    pomIncludeRepository := { _ => false },
    pomExtra := (
      <url>http://recipegrace.com/recipegrace</url>
        <licenses>
          <license>
            <name>BSD-style</name>
            <url>http://www.opensource.org/licenses/bsd-license.php</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:recipegrace/BigLibrary.git</url>
          <connection>scm:git:git@github.com:recipegrace/BigLibrary.git</connection>
        </scm>
        <developers>
          <developer>
            <id>feroshjacob</id>
            <name>Ferosh Jacob</name>
            <url>http://www.feroshjacob.com</url>
          </developer>
        </developers>),
    resolvers ++= Resolvers.allResolvers)

}
