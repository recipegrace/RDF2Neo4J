import sbt._

object BuildElectric extends Build {

  import CoreSettings._

  


  lazy val rdf2neo4j = (project in file(".")).
    settings(coreSettings: _*)

}



