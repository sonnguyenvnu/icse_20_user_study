@Override protected Map.Entry<String,TermFrequency> onGenerateEntry(String line){
  String[] param=line.split(delimeter);
  return new AbstractMap.SimpleEntry<String,TermFrequency>(param[0],new TermFrequency(param[0],Integer.valueOf(param[1])));
}
