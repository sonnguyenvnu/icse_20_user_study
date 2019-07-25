@Override public ArrayContainer and(final ArrayContainer value2){
  final ArrayContainer answer=new ArrayContainer(value2.content.length);
  int c=value2.cardinality;
  for (int k=0; k < c; ++k) {
    short v=value2.content[k];
    answer.content[answer.cardinality]=v;
    answer.cardinality+=this.bitValue(v);
  }
  return answer;
}
