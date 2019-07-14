@Override public V[] getValueArray(V[] a){
  if (a.length < size)   a=(V[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(),size);
  int i=0;
  for (  Map.Entry<String,V> entry : entrySet()) {
    a[i++]=entry.getValue();
  }
  return a;
}
