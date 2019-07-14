public static Map<String,double[]> evaluateNER(NERecognizer recognizer,String goldFile){
  Map<String,double[]> scores=new TreeMap<String,double[]>();
  double[] avg=new double[]{0,0,0};
  scores.put("avg.",avg);
  NERTagSet tagSet=recognizer.getNERTagSet();
  IOUtil.LineIterator lineIterator=new IOUtil.LineIterator(goldFile);
  for (  String line : lineIterator) {
    line=line.trim();
    if (line.isEmpty())     continue;
    Sentence sentence=Sentence.create(line);
    if (sentence == null)     continue;
    String[][] table=reshapeNER(convertSentenceToNER(sentence,tagSet));
    Set<String> pred=combineNER(recognizer.recognize(table[0],table[1]),tagSet);
    Set<String> gold=combineNER(table[2],tagSet);
    for (    String p : pred) {
      String type=p.split("\t")[2];
      double[] s=scores.get(type);
      if (s == null) {
        s=new double[]{0,0,0};
        scores.put(type,s);
      }
      if (gold.contains(p)) {
        ++s[2];
        ++avg[2];
      }
      ++s[0];
      ++avg[0];
    }
    for (    String g : gold) {
      String type=g.split("\t")[2];
      double[] s=scores.get(type);
      if (s == null) {
        s=new double[]{0,0,0};
        scores.put(type,s);
      }
      ++s[1];
      ++avg[1];
    }
  }
  for (  double[] s : scores.values()) {
    if (s[2] == 0) {
      s[0]=0;
      s[1]=0;
      continue;
    }
    s[1]=s[2] / s[1] * 100;
    s[0]=s[2] / s[0] * 100;
    s[2]=2 * s[0] * s[1] / (s[0] + s[1]);
  }
  return scores;
}
