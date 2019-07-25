/** 
 * @param ain Opcode to check.
 * @return Opcode is not linked to any other node.
 */
public static boolean isolated(AbstractInsnNode ain){
  return ain.getNext() == null && ain.getPrevious() == null;
}
