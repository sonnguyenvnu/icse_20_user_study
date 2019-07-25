/** 
 * Initializes the object.
 */
@Override public void start(ClassPool pool) throws NotFoundException {
  classPool=pool;
  final String msg="org.hotswap.agent.javassist.tools.reflect.Sample is not found or broken.";
  try {
    CtClass c=classPool.get("org.hotswap.agenta.javassist.tools.reflect.Sample");
    rebuildClassFile(c.getClassFile());
    trapMethod=c.getDeclaredMethod("trap");
    trapStaticMethod=c.getDeclaredMethod("trapStatic");
    trapRead=c.getDeclaredMethod("trapRead");
    trapWrite=c.getDeclaredMethod("trapWrite");
    readParam=new CtClass[]{classPool.get("java.lang.Object")};
  }
 catch (  NotFoundException e) {
    throw new RuntimeException(msg);
  }
catch (  BadBytecode e) {
    throw new RuntimeException(msg);
  }
}
