/** 
 * Calculate the index of an opcode in the given method.
 * @param ain Opcode.
 * @param method Method containing the opcode.
 * @return Opcode index.
 */
public static int index(AbstractInsnNode ain,MethodNode method){
  if (method != null) {
    return method.instructions.indexOf(ain);
  }
  int index=0;
  while (ain.getPrevious() != null) {
    ain=ain.getPrevious();
    index++;
  }
  return index;
}
