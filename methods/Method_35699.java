public static TextType determineTextType(String content){
  try {
    Json.read(content,JsonNode.class);
    return JSON;
  }
 catch (  Exception e) {
    try {
      Xml.read(content);
      return TextType.XML;
    }
 catch (    Exception e1) {
      return TextType.PLAIN_TEXT;
    }
  }
}
