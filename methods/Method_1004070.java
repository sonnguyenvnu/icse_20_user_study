private static void filter(final IFilterOutput output,final List<TryCatchBlockNode> tryCatchBlocks,final TryCatchBlockNode catchAnyBlock){
  final AbstractInsnNode e=next(catchAnyBlock.handler);
  final int size=size(e);
  if (size <= 0) {
    return;
  }
  final Set<AbstractInsnNode> inside=new HashSet<AbstractInsnNode>();
  for (  final TryCatchBlockNode t : tryCatchBlocks) {
    if (t.handler == catchAnyBlock.handler) {
      AbstractInsnNode i=t.start;
      while (i != t.end) {
        inside.add(i);
        i=i.getNext();
      }
    }
  }
  for (  final TryCatchBlockNode t : tryCatchBlocks) {
    if (t.handler == catchAnyBlock.handler) {
      boolean continues=false;
      AbstractInsnNode i=t.start;
      while (i != t.end) {
switch (i.getType()) {
case AbstractInsnNode.FRAME:
case AbstractInsnNode.LINE:
case AbstractInsnNode.LABEL:
          break;
case AbstractInsnNode.JUMP_INSN:
        final AbstractInsnNode jumpTarget=next(((JumpInsnNode)i).label);
      if (!inside.contains(jumpTarget)) {
        merge(output,size,e,jumpTarget);
      }
    continues=i.getOpcode() != Opcodes.GOTO;
  break;
default :
switch (i.getOpcode()) {
case Opcodes.IRETURN:
case Opcodes.LRETURN:
case Opcodes.FRETURN:
case Opcodes.DRETURN:
case Opcodes.ARETURN:
case Opcodes.RETURN:
case Opcodes.ATHROW:
  continues=false;
break;
default :
continues=true;
break;
}
break;
}
i=i.getNext();
}
i=next(i);
if (continues && !inside.contains(i)) {
merge(output,size,e,i);
}
}
if (t != catchAnyBlock && t.start == catchAnyBlock.start && t.end == catchAnyBlock.end) {
final AbstractInsnNode i=next(next(t.handler));
if (!inside.contains(i)) {
merge(output,size,e,i);
}
}
}
}
