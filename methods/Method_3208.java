private float computeEntropy(Map<Character,int[]> storage){
  float sum=0;
  for (  Map.Entry<Character,int[]> entry : storage.entrySet()) {
    float p=entry.getValue()[0] / (float)frequency;
    sum-=p * Math.log(p);
  }
  return sum;
}
