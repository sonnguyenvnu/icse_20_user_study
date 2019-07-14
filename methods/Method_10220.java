private static void testUnsignedInt(){
  try {
    Integer.parseUnsignedInt("-123",10);
  }
 catch (  NumberFormatException e) {
    System.out.println(e.getMessage());
  }
  long maxUnsignedInt=(1l << 32) - 1;
  System.out.println(maxUnsignedInt);
  String string=String.valueOf(maxUnsignedInt);
  int unsignedInt=Integer.parseUnsignedInt(string,10);
  System.out.println(unsignedInt);
  String string2=Integer.toUnsignedString(unsignedInt,10);
  System.out.println(string2);
  try {
    Integer.parseInt(string,10);
  }
 catch (  NumberFormatException e) {
    System.err.println("could not parse signed int of " + maxUnsignedInt);
  }
}
