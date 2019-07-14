/** 
 * Finds generic method for the given bridge method.
 * @param bridgeMethod the bridge method
 * @param aClass       the type where the bridge method is declared
 * @return generic method
 * @throws IOException
 * @throws NoSuchMethodException
 * @throws ClassNotFoundException
 */
public Method unbride(final Method bridgeMethod,Class<?> aClass) throws IOException, NoSuchMethodException, ClassNotFoundException {
  if (bridgeMethod.isBridge() && bridgeMethod.isSynthetic()) {
    if (cache.containsKey(bridgeMethod)) {
      return cache.get(bridgeMethod);
    }
    ClassReader classReader=new ClassReader(aClass.getName());
    final MethodSignature methodSignature=new MethodSignature();
    classReader.accept(new ClassVisitor(ASM5){
      @Override public MethodVisitor visitMethod(      int access,      String name,      String desc,      String signature,      String[] exceptions){
        boolean bridge=(access & ACC_BRIDGE) != 0 && (access & ACC_SYNTHETIC) != 0;
        if (bridge && bridgeMethod.getName().equals(name) && getParameterCount(desc) == bridgeMethod.getParameterTypes().length) {
          return new MethodFinder(methodSignature);
        }
        return super.visitMethod(access,name,desc,signature,exceptions);
      }
    }
,0);
    Method method=aClass.getDeclaredMethod(methodSignature.name,methodSignature.getParameterTypes());
    cache.put(bridgeMethod,method);
    return method;
  }
 else {
    return bridgeMethod;
  }
}
