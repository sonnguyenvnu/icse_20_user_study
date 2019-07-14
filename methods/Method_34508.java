private static int toInt(String propName,String propValue) throws IllegalArgumentException {
  try {
    return Integer.parseInt(propValue);
  }
 catch (  NumberFormatException e) {
    throw new IllegalArgumentException("bad property value. property name '" + propName + "'. Expected int value, actual = " + propValue);
  }
}
