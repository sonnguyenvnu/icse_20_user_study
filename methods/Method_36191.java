private static String formatIfJsonOrXml(ContentPattern<?> pattern,Body body){
  if (body == null || body.isAbsent()) {
    return "";
  }
  try {
    return pattern.getClass().equals(EqualToJsonPattern.class) ? Json.prettyPrint(Json.write(body.asJson())) : pattern.getClass().equals(EqualToXmlPattern.class) ? Xml.prettyPrint(body.asString()) : pattern.getClass().equals(BinaryEqualToPattern.class) ? body.asBase64() : body.asString();
  }
 catch (  Exception e) {
    return body.asString();
  }
}
