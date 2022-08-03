package lectures.part2oop

object Enums {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // add fields or methods
    def openDocument(): Unit = {
      if (this == READ) println("opening document")
      else println("reading not allowed")
    }
  }

  // constructor arguments
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4)      // 100
    case WRITE extends PermissionsWithBits(2)     // 010
    case EXECUTE extends PermissionsWithBits(1)   // 001
    case NONE extends PermissionsWithBits(0)      // 000
  }

  // companion object
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = {
      PermissionsWithBits.NONE
    }
  }

  val somePermissions: Permissions = Permissions.READ
  val somePermissionsWithBits: PermissionsWithBits = PermissionsWithBits.EXECUTE
  val callFromBits: PermissionsWithBits = PermissionsWithBits.fromBits(2)

  // standard API
  val somePermissionsOrdinal: Int = somePermissions.ordinal

  val allPermissions = PermissionsWithBits.values // returns an array of all possible values of the enum

  val readPermission: Permissions = Permissions.valueOf("READ") // returns Permissions.READ

  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionsWithBits)
    println(callFromBits)
    println(somePermissionsOrdinal)
    println(allPermissions)
    println(readPermission)
  }

}
