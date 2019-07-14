public static Document create(String param){
  Pattern pattern=Pattern.compile(".+?((?/w)|(?/w )|(?/w )|\\n|$)");
  Matcher matcher=pattern.matcher(param);
  List<Sentence> sentenceList=new LinkedList<Sentence>();
  while (matcher.find()) {
    String single=matcher.group();
    Sentence sentence=Sentence.create(single);
    if (sentence == null) {
      logger.warning("??" + single + "??????");
      return null;
    }
    sentenceList.add(sentence);
  }
  return new Document(sentenceList);
}
