public void insert(int index,String[] values){
  if (index < 0) {
    throw new IllegalArgumentException("insert() index cannot be negative: it was " + index);
  }
  if (index >= data.length) {
    throw new IllegalArgumentException("insert() index " + index + " is past the end of this list");
  }
  String[] temp=new String[count + values.length];
  System.arraycopy(data,0,temp,0,Math.min(count,index));
  System.arraycopy(values,0,temp,index,values.length);
  System.arraycopy(data,index,temp,index + values.length,count - index);
  count=count + values.length;
  data=temp;
}
