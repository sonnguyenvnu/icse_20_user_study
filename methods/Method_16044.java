@Override public boolean matches(Method method,Class<?> aClass){
  Lock lock=AopUtils.findMethodAnnotation(aClass,method,Lock.class);
  if (null != lock) {
    return true;
  }
  ReadLock readLock=AopUtils.findMethodAnnotation(aClass,method,ReadLock.class);
  if (null != readLock) {
    return true;
  }
  WriteLock writeLock=AopUtils.findMethodAnnotation(aClass,method,WriteLock.class);
  if (null != writeLock) {
    return true;
  }
  return false;
}
