private int parseAbsoluteLength(String length){
  if (length.endsWith("px")) {
    length=length.substring(0,length.indexOf("px"));
  }
  try {
    return Integer.parseInt(length);
  }
 catch (  NumberFormatException e) {
    return 0;
  }
}
