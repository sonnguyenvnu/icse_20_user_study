@Override public MappeableContainer add(int begin,int end){
  if (end == begin) {
    return clone();
  }
  if ((begin > end) || (end > (1 << 16))) {
    throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
  }
  int indexstart=BufferUtil.unsignedBinarySearch(content,0,cardinality,(short)begin);
  if (indexstart < 0) {
    indexstart=-indexstart - 1;
  }
  int indexend=BufferUtil.unsignedBinarySearch(content,0,cardinality,(short)(end - 1));
  if (indexend < 0) {
    indexend=-indexend - 1;
  }
 else {
    indexend++;
  }
  int rangelength=end - begin;
  int newcardinality=indexstart + (cardinality - indexend) + rangelength;
  if (newcardinality > DEFAULT_MAX_SIZE) {
    MappeableBitmapContainer a=this.toBitmapContainer();
    return a.iadd(begin,end);
  }
  MappeableArrayContainer answer=new MappeableArrayContainer(newcardinality,content);
  if (!BufferUtil.isBackedBySimpleArray(answer.content)) {
    throw new RuntimeException("Should not happen. Internal bug.");
  }
  BufferUtil.arraycopy(content,indexend,answer.content,indexstart + rangelength,cardinality - indexend);
  short[] answerarray=answer.content.array();
  for (int k=0; k < rangelength; ++k) {
    answerarray[k + indexstart]=(short)(begin + k);
  }
  answer.cardinality=newcardinality;
  return answer;
}
