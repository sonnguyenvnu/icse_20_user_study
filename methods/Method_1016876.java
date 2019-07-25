public void add(int index,double value){
  if (values == null && value != 1.0)   throw new IllegalArgumentException("Trying to add non-1.0 value (" + dictionary.lookupObject(index) + "=" + value + ") to binary vector");
  assert (index >= 0);
  if (indices == null) {
    if (index >= values.length) {
      int newLength=index + 10;
      double[] newValues=new double[newLength];
      System.arraycopy(values,0,newValues,0,values.length);
      values=newValues;
      values[index]=value;
      assert (size <= index);
    }
 else {
      values[index]+=value;
    }
    if (size <= index)     size=index + 1;
  }
 else {
    if (size == indices.length) {
      int newLength;
      if (indices.length == 0)       newLength=4;
 else       if (indices.length < 4)       newLength=indices.length * 2;
 else       if (indices.length < 100)       newLength=(indices.length * 3) / 2;
 else       newLength=indices.length + 150;
      if (values != null) {
        double[] newValues=new double[newLength];
        System.arraycopy(values,0,newValues,0,values.length);
        values=newValues;
      }
      int[] newIndices=new int[newLength];
      System.arraycopy(indices,0,newIndices,0,indices.length);
      indices=newIndices;
    }
    indices[size]=index;
    if (values != null)     values[size]=value;
    size++;
  }
}
