public int removeValues(int value){
  int ii=0;
  if (Float.isNaN(value)) {
    for (int i=0; i < count; i++) {
      if (!Float.isNaN(data[i])) {
        data[ii++]=data[i];
      }
    }
  }
 else {
    for (int i=0; i < count; i++) {
      if (data[i] != value) {
        data[ii++]=data[i];
      }
    }
  }
  int removed=count - ii;
  count=ii;
  return removed;
}
