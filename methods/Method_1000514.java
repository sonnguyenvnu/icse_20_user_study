public Object calculate(){
  Object obj=getLeft();
  if (null == obj)   return false;
  if (obj instanceof Boolean)   return (Boolean)obj;
  return Castors.me().castTo(obj,Boolean.class);
}
