package oops

object Enums extends App {
  enum Permissions{
    case READ, WRITE, EXECUTE, NONE

    //add fields, methods
    def openDocument(): Unit =
      if(this == READ) println("Opening Document")
      else println("Reading not allowed")
  }

  val somePermission: Permissions = Permissions.READ
  println(somePermission)

  somePermission.openDocument()

  enum PermisisonWithBits(bits: Int){
    case READ extends PermisisonWithBits(4)
    case WRITE extends PermisisonWithBits(2)
    case EXECUTE extends PermisisonWithBits(1)
    case NONE extends PermisisonWithBits(0)
  }

  object PermisisonWithBits{
    def fromBits(bits: Int): PermisisonWithBits = PermisisonWithBits.NONE
  }

  //standard API
  val somePermissionOrdinal = somePermission.ordinal
  val allPermissions = PermisisonWithBits.values
  val readPermissions = Permissions.valueOf("READ")
  println(allPermissions)
}
