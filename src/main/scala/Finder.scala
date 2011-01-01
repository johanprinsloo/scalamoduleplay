package org.scalamoduleplay

import com.weiglewilczek.scalamodules._
import org.osgi.framework.{BundleActivator, BundleContext}

class Finder extends BundleActivator {

  override def start(context: BundleContext) {

    println("Find a Handshake service and print the result of calling init:")
    context findService withInterface[Handshake] andApply {
      _.init
    } match {
      case None => println("No Handshake service available!")
      case Some(init) => println(init)
    }

    println("""Find all Handshake services and print their "style" property plus the result of calling init:""")
    context findServices withInterface[Handshake] andApply {
      (handshake, properties) => "%s: %s".format(properties get "style" getOrElse "UNKNOWN", handshake.init)
    } match {
      case Nil => println("No Handshake service available!")
      case inits => inits foreach println
    }

    println("""Find all Handshake services matching the filter "style".present and print their "style" property plus the result of calling init:""")
    context findServices withInterface[Handshake] withFilter "style".present andApply {
      (handshake, properties) => "%s: %s".format(properties("style"), handshake.init)
    } match {
      case Nil => println("No Handshake service available!")
      case inits => inits foreach println
    }
  }

  override def stop(context: BundleContext) {}
}