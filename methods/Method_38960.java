@Override public void removeAttribute(final int index){
  if (index >= attributesCount) {
    throw new IndexOutOfBoundsException();
  }
  System.arraycopy(attrNames,index + 1,attrNames,index,attributesCount - index);
  System.arraycopy(attrValues,index + 1,attrValues,index,attributesCount - index);
  attributesCount--;
  modified=true;
}
