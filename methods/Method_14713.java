public static int getInteger(final String name,final int def){
  final String val=get(name);
  try {
    return (val == null) ? def : Integer.parseInt(val);
  }
 catch (  NumberFormatException e) {
    throw new RuntimeException("Could not parse '" + val + "' as an integer number.",e);
  }
}
