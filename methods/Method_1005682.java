/** 
 * Declares  {@code type}.
 * @param flags a bitwise combination of {@link Modifier#PUBLIC},  {@link Modifier#FINAL} and {@link Modifier#ABSTRACT}.
 */
public void declare(TypeId<?> type,String sourceFile,int flags,TypeId<?> supertype,TypeId<?>... interfaces){
  TypeDeclaration declaration=getTypeDeclaration(type);
  int supportedFlags=Modifier.PUBLIC | Modifier.FINAL | Modifier.ABSTRACT | AccessFlags.ACC_SYNTHETIC;
  if ((flags & ~supportedFlags) != 0) {
    throw new IllegalArgumentException("Unexpected flag: " + Integer.toHexString(flags));
  }
  if (declaration.declared) {
    throw new IllegalStateException("already declared: " + type);
  }
  declaration.declared=true;
  declaration.flags=flags;
  declaration.supertype=supertype;
  declaration.sourceFile=sourceFile;
  declaration.interfaces=new TypeList(interfaces);
}
