@SuppressWarnings({"SuspiciousSystemArraycopy"}) protected Object ensureArraySize(final BeanProperty bp,Object array,final Class componentType,final int index){
  int len=Array.getLength(array);
  if (index >= len) {
    Object newArray=Array.newInstance(componentType,index + 1);
    System.arraycopy(array,0,newArray,0,len);
    Setter setter=bp.getSetter(true);
    if (setter == null) {
      throw new BeanException("Setter or field not found: " + bp.name,bp);
    }
    newArray=invokeSetter(setter,bp,newArray);
    array=newArray;
  }
  return array;
}
