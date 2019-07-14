private static <V>V[] grow(final V[] array){
  @SuppressWarnings("unchecked") final V[] newArray=(V[])Array.newInstance(array.getClass().getComponentType(),array.length * 2);
  System.arraycopy(array,0,newArray,0,array.length);
  return newArray;
}
