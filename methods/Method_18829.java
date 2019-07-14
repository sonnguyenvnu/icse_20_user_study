private static String getParameterizedFieldType(String type){
  final int indexOfStartDiamond=type.indexOf('<');
  final int indexOfEndDiamond=type.indexOf('>');
  return type.substring(indexOfStartDiamond + 1,indexOfEndDiamond);
}
