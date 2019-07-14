@Override public T create(String type){
  Class<? extends T> clz=mSparseArray.get(type);
  if (clz != null) {
    try {
      return clz.newInstance();
    }
 catch (    InstantiationException e) {
      if (TangramBuilder.isPrintLog())       Log.e(TAG,e.getMessage(),e);
    }
catch (    IllegalAccessException e) {
      if (TangramBuilder.isPrintLog())       Log.e(TAG,e.getMessage(),e);
    }
  }
 else   if (TangramBuilder.isPrintLog()) {
    LogUtils.e("ClassResolver","Can not find type: " + type + " in ClassResolver");
  }
  return null;
}
