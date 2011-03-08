package org.test.scalamoduleplay

import java.io.Serializable
import com.weiglewilczek.scalamodules._
import org.osgi.framework.{BundleActivator, BundleContext}

class Activator extends BundleActivator {

  override def start(context: BundleContext) {
    println("Bundle start: org.scalamoduleplay")
    val handshake = new Handshake {
      override def init = "Init!"
      override def register = "Register!"
    }
    context createService handshake

    val altHandshake = new Handshake {
      override def init = "Alt Init!"
      override def register = "Alt Register!"
    }
    context.createService(altHandshake, Style -> "alt", interface[Handshake])

    val alt2Handshake = new Handshake with Serializable {
      override def init = "Alt2 Init!"
      override def register = "Alt2 Register!"
    }

    context.createService(
      alt2Handshake,
      interface1 = interface[Handshake],
      interface2 = interface[Serializable],
      properties = Map(Style -> "alt2", "priority" -> 1))
  }

  override def stop(context: BundleContext) {
    println("Bundle stop: org.scalamoduleplay")
  }

  private val Style = "style"
}