public void start(Member member,XposedBridge.AdditionalHookInfo hookInfo,ClassLoader appClassLoader,String dexDirPath) throws Exception {
  if (member instanceof Method) {
    Method method=(Method)member;
    mIsStatic=Modifier.isStatic(method.getModifiers());
    mReturnType=method.getReturnType();
    if (mReturnType.equals(Void.class) || mReturnType.equals(void.class) || mReturnType.isPrimitive()) {
      mReturnTypeId=TypeId.get(mReturnType);
    }
 else {
      mReturnType=Object.class;
      mReturnTypeId=TypeId.OBJECT;
    }
    mParameterTypeIds=getParameterTypeIds(method.getParameterTypes(),mIsStatic);
    mActualParameterTypes=getParameterTypes(method.getParameterTypes(),mIsStatic);
    mHasThrowable=method.getExceptionTypes().length > 0;
  }
 else   if (member instanceof Constructor) {
    Constructor constructor=(Constructor)member;
    mIsStatic=false;
    mReturnType=void.class;
    mReturnTypeId=TypeId.VOID;
    mParameterTypeIds=getParameterTypeIds(constructor.getParameterTypes(),mIsStatic);
    mActualParameterTypes=getParameterTypes(constructor.getParameterTypes(),mIsStatic);
    mHasThrowable=constructor.getExceptionTypes().length > 0;
  }
 else   if (member.getDeclaringClass().isInterface()) {
    throw new IllegalArgumentException("Cannot hook interfaces: " + member.toString());
  }
 else   if (Modifier.isAbstract(member.getModifiers())) {
    throw new IllegalArgumentException("Cannot hook abstract methods: " + member.toString());
  }
 else {
    throw new IllegalArgumentException("Only methods and constructors can be hooked: " + member.toString());
  }
  mMember=member;
  mHookInfo=hookInfo;
  mDexDirPath=dexDirPath;
  if (appClassLoader == null || appClassLoader.getClass().getName().equals("java.lang.BootClassLoader")) {
    mAppClassLoader=this.getClass().getClassLoader();
  }
 else {
    mAppClassLoader=appClassLoader;
  }
  doMake();
}
