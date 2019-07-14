private static String unknownRendererMessage(String userSpecifiedType){
  String[] typeCodes=validRendererCodes();
  StringBuilder sb=new StringBuilder(100);
  sb.append("Formatter type must be one of: '").append(typeCodes[0]);
  for (int i=1; i < typeCodes.length; i++) {
    sb.append("', '").append(typeCodes[i]);
  }
  sb.append("', or a class name; you specified: ").append(userSpecifiedType);
  return sb.toString();
}
