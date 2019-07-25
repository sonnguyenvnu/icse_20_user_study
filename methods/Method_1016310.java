/** 
 * Creates a new backing array with the specified capacity containing the current items.
 * @param newCapacity the new capacity 
 */
protected void resize(int newCapacity){
  @SuppressWarnings("unchecked") T[] newItems=(T[])ArrayReflection.newInstance(items.getClass().getComponentType(),newCapacity);
  if (tail > head) {
    System.arraycopy(items,head,newItems,0,size);
  }
 else   if (size > 0) {
    System.arraycopy(items,head,newItems,0,items.length - head);
    System.arraycopy(items,0,newItems,items.length - head,tail);
  }
  head=0;
  tail=size;
  items=newItems;
}
