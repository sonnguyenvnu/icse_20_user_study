@Override protected boolean contains(RunContainer runContainer){
  int i1=0, i2=0;
  while (i1 < numberOfRuns() && i2 < runContainer.numberOfRuns()) {
    int start1=toIntUnsigned(getValue(i1));
    int stop1=start1 + toIntUnsigned(getLength(i1));
    int start2=toIntUnsigned(runContainer.getValue(i2));
    int stop2=start2 + toIntUnsigned(runContainer.getLength(i2));
    if (start1 > start2) {
      return false;
    }
 else {
      if (stop1 > stop2) {
        i2++;
      }
 else       if (stop1 == stop2) {
        i1++;
        i2++;
      }
 else {
        i1++;
      }
    }
  }
  return i2 == runContainer.numberOfRuns();
}
