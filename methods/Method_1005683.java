/** 
 * Declares a method or constructor.
 * @param flags a bitwise combination of {@link Modifier#PUBLIC},  {@link Modifier#PRIVATE},  {@link Modifier#PROTECTED},  {@link Modifier#STATIC}, {@link Modifier#FINAL} and {@link Modifier#SYNCHRONIZED}. <p><strong>Warning:</strong> the  {@link Modifier#SYNCHRONIZED} flagis insufficient to generate a synchronized method. You must also use {@link Code#monitorEnter} and {@link Code#monitorExit} to acquirea monitor.
 */
public Code declare(MethodId<?,?> method,int flags){
  TypeDeclaration typeDeclaration=getTypeDeclaration(method.declaringType);
  if (typeDeclaration.methods.containsKey(method)) {
    throw new IllegalStateException("already declared: " + method);
  }
  int supportedFlags=Modifier.PUBLIC | Modifier.PRIVATE | Modifier.PROTECTED | Modifier.STATIC | Modifier.FINAL | Modifier.SYNCHRONIZED | AccessFlags.ACC_SYNTHETIC | AccessFlags.ACC_BRIDGE;
  if ((flags & ~supportedFlags) != 0) {
    throw new IllegalArgumentException("Unexpected flag: " + Integer.toHexString(flags));
  }
  if ((flags & Modifier.SYNCHRONIZED) != 0) {
    flags=(flags & ~Modifier.SYNCHRONIZED) | AccessFlags.ACC_DECLARED_SYNCHRONIZED;
  }
  if (method.isConstructor() || method.isStaticInitializer()) {
    flags|=ACC_CONSTRUCTOR;
  }
  MethodDeclaration methodDeclaration=new MethodDeclaration(method,flags);
  typeDeclaration.methods.put(method,methodDeclaration);
  return methodDeclaration.code;
}
