public Object convert(String[] ss){
  if (null == ss)   return null;
  Object re=Array.newInstance(eleType,ss.length);
  for (int i=0; i < ss.length; i++) {
    Array.set(re,i,convertor.convert(Lang.array(ss[i])));
  }
  return re;
}
