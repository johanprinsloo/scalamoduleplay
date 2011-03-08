package org.test.scalamoduleplay

import org.osgi.framework.Bundle
import org.osgi.framework.BundleContext
import org.osgi.framework.BundleException
import org.osgi.framework.launch.Framework
import org.osgi.framework.launch.FrameworkFactory
import java.util.ServiceLoader
import collection.mutable.{LinkedList, HashMap}
import java.io.File

object Runpoint {
  def main(args: Array[String]): Unit = {
    val frameworkFactory: FrameworkFactory = ServiceLoader.load(classOf[FrameworkFactory]).iterator.next
    val currDir = System.getProperty("user.dir")
    var config = new java.util.HashMap[String, String]
    var framework: Framework = frameworkFactory.newFramework(config)
    try {
      framework.start
    }
    catch {
      case e: BundleException => {
        e.printStackTrace
      }
    }
    val context = framework.getBundleContext()
    val installedBundles = new LinkedList[Bundle]
    try {
      installedBundles :+ context.installBundle("file:"+ currDir
        + "//lib_managed//scala_2.8.1//compile//org.apache.felix.shell-1.4.1.jar")
      installedBundles :+ context.installBundle("file:" + currDir
        + "//lib_managed//scala_2.8.1//compile//org.apache.felix.shell.tui-1.4.1.jar")
      for (bundle <- installedBundles) {
        bundle.start
      }
    }
    catch {
      case e: BundleException => {
        e.printStackTrace
      }
    }
  }
}