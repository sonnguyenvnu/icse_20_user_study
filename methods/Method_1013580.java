public final Object clone(){
  ValueVec v=new ValueVec(this.elementData.length);
  System.arraycopy(elementData,0,v.elementData,0,elementCount);
  v.elementCount=elementCount;
  return v;
}
