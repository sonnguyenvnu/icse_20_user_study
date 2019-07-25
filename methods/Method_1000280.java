public boolean match(Method method){
  int mod=method.getModifiers();
  String name=method.getName();
  if (null != ignore)   if (ignore.matcher(name).find())   return false;
  if (null != active)   if (!active.matcher(name).find())   return false;
  if (mods <= 0)   return true;
  if (mod == 0)   mod|=TRANSIENT;
  return Maths.isMask(mod,mods);
}
