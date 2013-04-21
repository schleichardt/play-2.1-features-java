import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-2-1-features-java"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    filters,
    "junit" % "junit-dep" % "4.11" % "test"
  )

  val adminDependencies = Seq("junit" % "junit-dep" % "4.11" % "test")

  lazy val admin = play.Project(appName + "-admin", appVersion, adminDependencies, path = file("modules/admin"))


  lazy  val main = play.Project(appName, appVersion, appDependencies).settings(
    playStage <<= (playStage, baseDirectory) map {(stageCommand, baseDir) =>
      val content = """#!/usr/bin/env sh
                      |
                      |exec authbind --deep java $@ -cp "`dirname $0`/staged/*" play.core.server.NettyServer `dirname $0`/..""".stripMargin
      IO.write(baseDir / "target" / "start", content)
      stageCommand
    }
  ).dependsOn(admin).aggregate(admin)

}
