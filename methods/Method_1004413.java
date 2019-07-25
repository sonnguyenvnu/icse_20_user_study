@Override public int times(){
  Object o=getProperty(keys.qmq_times);
  if (o == null)   return 1;
  return Integer.valueOf(o.toString());
}
