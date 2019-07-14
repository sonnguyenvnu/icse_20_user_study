private String[] newValues(final String name,final Object value){
  if (this.headers.containsKey(name)) {
    String[] values=this.headers.get(name);
    String[] newValues=new String[values.length + 1];
    System.arraycopy(values,0,newValues,0,values.length);
    newValues[values.length]=value.toString();
    return newValues;
  }
  return new String[]{value.toString()};
}
