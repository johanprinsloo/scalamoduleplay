import com.weiglewilczek.bnd4sbt.BNDPlugin
import sbt._

class scalamoduleplay(info: ProjectInfo) extends DefaultProject(info) with BNDPlugin {


  // Versions
  val osgiVersion = "4.2.0"
  val paxExamVersion = "1.2.0"
  val scalaModulesVersion = "2.0.2"

  // Compile
  val scalaModulesCore = "com.weiglewilczek.scalamodules" % "scalamodules-core_2.8.0" % "2.0.2" withSources
  val felix = "org.apache.felix" % "org.apache.felix.framework" % "3.0.9" // intransitive()
  val felixgogo_rt = "org.apache.felix" % "org.apache.felix.gogo.runtime" % "0.8.0"
  val felixgogo_cmd = "org.apache.felix" % "org.apache.felix.gogo.command" % "0.8.0"
  val felixgogo_sh = "org.apache.felix" % "org.apache.felix.gogo.shell" % "0.8.0"
  val felixshell_tui = "org.apache.felix" % "org.apache.felix.shell.tui" % "1.4.1"


  // Provided
  val osgiCore = "org.osgi" % "org.osgi.core" % osgiVersion % "provided"

  override def bndExportPackage =
    "org.test.scalamoduleplay.Handshake;version=%s".format(projectVersion.value) :: Nil

  override def bndVersionPolicy = Some("[$(@),$(version;=+;$(@)))")

}