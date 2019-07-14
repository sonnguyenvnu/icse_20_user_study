/** 
 * ????????????BM25???
 * @param sentence ????????
 * @param index    ??????????????
 * @return BM25 score
 */
public double sim(List<String> sentence,int index){
  double score=0;
  for (  String word : sentence) {
    if (!f[index].containsKey(word))     continue;
    int d=docs.get(index).size();
    Integer tf=f[index].get(word);
    score+=(idf.get(word) * tf * (k1 + 1) / (tf + k1 * (1 - b + b * d / avgdl)));
  }
  return score;
}
