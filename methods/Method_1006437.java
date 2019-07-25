@Override public int rank(short lowbits){
  int x=toIntUnsigned(lowbits);
  int answer=0;
  for (int k=0; k < this.nbrruns; ++k) {
    int value=toIntUnsigned(getValue(k));
    int length=toIntUnsigned(getLength(k));
    if (x < value) {
      return answer;
    }
 else     if (value + length + 1 > x) {
      return answer + x - value + 1;
    }
    answer+=length + 1;
  }
  return answer;
}
