public int[] values(){
  int size=size();
  int[] newValues=new int[size];
  int j=0;
  int length=this.values.length;
  for (int i=0; i < length; i++) {
    if (this.values[i] != -1) {
      newValues[j++]=this.values[i];
    }
  }
  return newValues;
}
