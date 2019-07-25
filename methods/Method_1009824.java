@Override public Object call(String methodName,Object... args) throws MethodNotFoundException {
  if (null == mService) {
    return null;
  }
  if (null == args) {
    args=new Object[0];
  }
  MethodInfo targetMethod=findMethod(methodName,args);
  Object resObj=null;
  if (null != targetMethod.mMethod) {
    try {
      if (targetMethod.mDynamicArgs) {
        resObj=targetMethod.mMethod.invoke(mService,reBuildArg(targetMethod.mFixedArgsLen,args));
      }
 else {
        resObj=targetMethod.mMethod.invoke(mService,args);
      }
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
  return resObj;
}
