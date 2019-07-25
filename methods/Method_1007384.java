/** 
 * Redefines classes.
 */
public static void redefine(Class<?>[] oldClasses,CtClass[] newClasses) throws NotFoundException, IOException, CannotCompileException {
  startAgent();
  ClassDefinition[] defs=new ClassDefinition[oldClasses.length];
  for (int i=0; i < oldClasses.length; i++)   defs[i]=new ClassDefinition(oldClasses[i],newClasses[i].toBytecode());
  try {
    instrumentation.redefineClasses(defs);
  }
 catch (  ClassNotFoundException e) {
    throw new NotFoundException(e.getMessage(),e);
  }
catch (  UnmodifiableClassException e) {
    throw new CannotCompileException(e.getMessage(),e);
  }
}
