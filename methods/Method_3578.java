@Override public Double similarity(IdVector other){
  Double score=0.0;
  for (  Long[] a : idArrayList) {
    for (    Long[] b : other.idArrayList) {
      Long distance=ArrayDistance.computeAverageDistance(a,b);
      score+=1.0 / (0.1 + distance);
    }
  }
  return score / other.idArrayList.size();
}
