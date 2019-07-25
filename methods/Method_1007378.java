/** 
 * Initializes the object. This is a method declared in javassist.Translator.
 * @see javassist.Translator#start(ClassPool)
 */
@Override public void start(ClassPool pool) throws NotFoundException {
  classPool=pool;
  CtClass c=pool.get(sampleClass);
  forwardMethod=c.getDeclaredMethod("forward");
  forwardStaticMethod=c.getDeclaredMethod("forwardStatic");
  proxyConstructorParamTypes=pool.get(new String[]{"org.hotswap.agent.javassist.tools.rmi.ObjectImporter","int"});
  interfacesForProxy=pool.get(new String[]{"java.io.Serializable","org.hotswap.agent.javassist.tools.rmi.Proxy"});
  exceptionForProxy=new CtClass[]{pool.get("org.hotswap.agent.javassist.tools.rmi.RemoteException")};
}
