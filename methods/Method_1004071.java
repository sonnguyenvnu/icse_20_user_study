/** 
 * @return number of instructions inside given "catch-any" handler
 */
private static int size(AbstractInsnNode i){
  if (Opcodes.ASTORE != i.getOpcode()) {
    return 0;
  }
  final int var=((VarInsnNode)i).var;
  int size=-1;
  do {
    size++;
    i=next(i);
    if (i == null) {
      return 0;
    }
  }
 while (!(Opcodes.ALOAD == i.getOpcode() && var == ((VarInsnNode)i).var));
  i=next(i);
  if (Opcodes.ATHROW != i.getOpcode()) {
    return 0;
  }
  return size;
}
