static public Object reverse(Object list){
  Class<?> type=list.getClass().getComponentType();
  int length=Array.getLength(list);
  Object outgoing=Array.newInstance(type,length);
  for (int i=0; i < length; i++) {
    Array.set(outgoing,i,Array.get(list,(length - 1) - i));
  }
  return outgoing;
}
