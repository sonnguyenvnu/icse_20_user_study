@Override public Object generate(Context context,Uri uri,@Nullable Class<?> target){
  if (target == null) {
    return null;
  }
  Object result=null;
  if (Activity.class.isAssignableFrom(target)) {
    result=new Intent(context,target);
  }
 else   if (Fragment.class.isAssignableFrom(target)) {
    try {
      result=target.newInstance();
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
  return result;
}
