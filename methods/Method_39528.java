/** 
 * Returns the class's access flags (see  {@link Opcodes}). This value may not reflect Deprecated and Synthetic flags when bytecode is before 1.5 and those flags are represented by attributes.
 * @return the class access flags.
 * @see ClassVisitor#visit(int,int,String,String,String,String[])
 */
public int getAccess(){
  return readUnsignedShort(header);
}
