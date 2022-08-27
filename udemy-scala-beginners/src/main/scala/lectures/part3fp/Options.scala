package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  def unsafeMethod(): String = null
  // val result = Some(unsafeMethod()) WRONG
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // Chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // Design APIs: Make your methods return Options if they return null
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod().orElse(betterBackupMethod())

  // Functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // Unsafe - Do not use this
  println(myFirstOption.map(_ * 2)) // prints Some(8)
  println(myFirstOption.filter(x => x > 10)) // prints None
  println(myFirstOption.flatMap(x => Option(x * 10)))

  println()

  val config: Map[String, String] = Map("host" -> "176.45.36.1", "port" -> "90")

  class Connection {
    def connect = "Connected" // connect to some server
  }
  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  val host = config.get("host")
  val port = config.get("port")

  /*
      if (h != null) {
        if (p != null) {
          return Connection.apply(h, p)
        }
      }
      return null;
  */
  val connection: Option[Connection] = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))

  /*
      if (c != null) {
        return c.connect;
      } else {
        return null;
      }
  */
  val connectionStatus: Option[String] = connection.map(c => c.connect)

  /* If the connectionStatus is None then nothing is printed to the console otherwise
     when connectionStatus is = Some(Connected) the Connected result is being printed to the console */

  /*
      if (connectionStatus == null) {
        println(None);
      } else {
        println(Some(connectionStatus.get))
      }
  */
  println(connectionStatus)

  /*
      if (connectionStatus != null) println(connectionStatus)
  */
  connectionStatus.foreach(println)

  /* Chained method solution */
  config.get("host")
    .flatMap(host => config.get("port")
    .flatMap(port => Connection(host, port))
    .map(connection => connection.connect))
    .foreach(println)

  /* For-Comprehensions */
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)

}
