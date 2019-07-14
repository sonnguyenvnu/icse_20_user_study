private T[] copyArray(T[] items){
  T[] copy=(T[])Array.newInstance(mTClass,items.length);
  System.arraycopy(items,0,copy,0,items.length);
  return copy;
}
