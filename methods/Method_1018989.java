/** 
 * Number of matching types at and before the given opcode.
 * @param type Type to check for.
 * @param ain Start index to count backwards from.
 * @return Number of matching types.
 */
public static int count(int type,AbstractInsnNode ain){
  int count=type == ain.getType() ? 1 : 0;
  if (ain.getPrevious() != null) {
    count+=count(type,ain.getPrevious());
  }
  return count;
}
