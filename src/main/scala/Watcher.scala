package org.test.scalamoduleplay

import com.weiglewilczek.scalamodules._
import org.osgi.framework.{BundleActivator, BundleContext}

class Watcher extends BundleActivator {

  override def start(context: BundleContext) {
    def message(property: Option[Any], s: String) =
      "ServiceEvent - %s: %s".format(property getOrElse "UNKNOWN", s)
    def styleProperty(properties: Props) = properties get "style"
    context watchServices withInterface[Handshake] withFilter "style".present andHandle {
      case AddingService(handshake, properties) => println(message(styleProperty(properties), handshake.init))
      case ServiceRemoved(handshake, properties) => println(message(styleProperty(properties), handshake.register))
    }
  }

  override def stop(context: BundleContext) { println("bundle stop")}
}