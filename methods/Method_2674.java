public V[] getValueArray(V[] a){
  int size=v.length;
  if (a.length < size)   a=(V[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(),size);
  System.arraycopy(v,0,a,0,size);
  return a;
}
