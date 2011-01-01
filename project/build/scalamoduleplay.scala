import com.weiglewilczek.bnd4sbt.BNDPlugin
import sbt._

class scalamoduleplay(info: ProjectInfo) extends DefaultProject(info) with BNDPlugin {


  // Versions
  val osgiVersion = "4.2.0"
  val paxExamVersion = "1.2.0"
  val scalaModulesVersion = "2.0.0"

  // Compile
  val scalaModulesCore = "com.weiglewilczek.scalamodules" % "scalamodules-core_2.8.0" % "2.0.0"

  // Provided
  val osgiCore = "org.osgi" % "org.osgi.core" % osgiVersion % "provided"

  override def bndExportPackage =
    "org.scalamoduleplay.Handshake;version=%s".format(projectVersion.value) :: Nil

  override def bndVersionPolicy = Some("[$(@),$(version;=+;$(@)))")

}