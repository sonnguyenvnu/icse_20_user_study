private static void insertIntoList(StringBuilder sb,List<String> sentences){
  String content=sb.toString().trim();
  if (content.length() > 0) {
    sentences.add(content);
  }
}
