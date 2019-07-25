@Override public boolean contains(int minimum,int supremum){
  int count=0;
  for (int i=0; i < numberOfRuns(); ++i) {
    int start=toIntUnsigned(getValue(i));
    int length=toIntUnsigned(getLength(i));
    int stop=start + length;
    if (start >= supremum) {
      break;
    }
    if (stop >= supremum) {
      count+=Math.max(0,supremum - start);
      break;
    }
    count+=Math.min(Math.max(0,stop - minimum),length);
  }
  return count >= supremum - minimum - 1;
}
