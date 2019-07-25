/** 
 * Absorb as many instructions until the next label or the next transfer of control instruction. In the first pass we may end up creating many many BBs because there may be a lot of non-target labels (esp. when debug information is available). The constraints are as follows: 1. A transfer of control instruction must be the last instruction. It  may also be the first (and only) instruction 2. A labeled instruction must be the first instruction in a BB. It may optionally be the last (and only) instruction 3. A pausable method is treated like a labeled instruction, and is  given a label if there isn't one already. Constraint 2 applies.
 */
@SuppressWarnings("unchecked") int initialize(int pos){
  AbstractInsnNode ain;
  startPos=pos;
  BasicBlock bb;
  boolean endOfBB=false;
  boolean hasFollower=true;
  int nextCatch=flow.mapHandler(pos + 1);
  int size=flow.instructions.size();
  for (; pos < size; pos++) {
    if (pos > startPos && (pos == nextCatch || flow.getLabelAt(pos) != null)) {
      pos--;
      hasFollower=true;
      endOfBB=true;
      break;
    }
    ain=getInstruction(pos);
    int opcode=ain.getOpcode();
switch (opcode) {
case ALOAD:
case ILOAD:
case LLOAD:
case FLOAD:
case DLOAD:
      usage.read(((VarInsnNode)ain).var);
    break;
case ISTORE:
case LSTORE:
case FSTORE:
case DSTORE:
case ASTORE:
  usage.write(((VarInsnNode)ain).var);
break;
case IINC:
int v=((IincInsnNode)ain).var;
usage.read(v);
usage.write(v);
break;
case IFEQ:
case IFNE:
case IFLT:
case IFGE:
case IFGT:
case IFLE:
case IFNULL:
case IFNONNULL:
case IF_ICMPEQ:
case IF_ICMPNE:
case IF_ICMPLT:
case IF_ICMPGE:
case IF_ICMPGT:
case IF_ICMPLE:
case IF_ACMPEQ:
case IF_ACMPNE:
case JSR:
case GOTO:
LabelNode l=((JumpInsnNode)ain).label;
bb=flow.getOrCreateBasicBlock(l);
if (opcode == JSR) {
bb.setFlag(IS_SUBROUTINE);
hasFollower=false;
}
addSuccessor(bb);
if (opcode == GOTO) {
hasFollower=false;
}
endOfBB=true;
break;
case RET:
case IRETURN:
case LRETURN:
case FRETURN:
case DRETURN:
case ARETURN:
case RETURN:
case ATHROW:
hasFollower=false;
endOfBB=true;
break;
case TABLESWITCH:
case LOOKUPSWITCH:
LabelNode defaultLabel;
List<LabelNode> otherLabels;
if (opcode == TABLESWITCH) {
defaultLabel=((TableSwitchInsnNode)ain).dflt;
otherLabels=((TableSwitchInsnNode)ain).labels;
}
 else {
defaultLabel=((LookupSwitchInsnNode)ain).dflt;
otherLabels=((LookupSwitchInsnNode)ain).labels;
}
for (Iterator<LabelNode> it=otherLabels.iterator(); it.hasNext(); ) {
l=it.next();
addSuccessor(flow.getOrCreateBasicBlock(l));
}
addSuccessor(flow.getOrCreateBasicBlock(defaultLabel));
endOfBB=true;
hasFollower=false;
break;
case INVOKEVIRTUAL:
case INVOKESTATIC:
case INVOKEINTERFACE:
case INVOKESPECIAL:
if (flow.isPausableMethodInsn((MethodInsnNode)ain)) {
LabelNode il=flow.getOrCreateLabelAtPos(pos);
if (pos == startPos) {
setFlag(PAUSABLE);
endOfBB=true;
}
 else {
bb=flow.getOrCreateBasicBlock(il);
bb.setFlag(PAUSABLE);
addSuccessor(bb);
pos--;
hasFollower=true;
endOfBB=true;
}
}
break;
default :
if (opcode >= 26 && opcode <= 45) throw new IllegalStateException("instruction variants not expected here");
break;
}
if (endOfBB) break;
}
endPos=pos;
if (hasFollower && (pos + 1) < flow.instructions.size()) {
LabelNode l=flow.getOrCreateLabelAtPos(pos + 1);
bb=flow.getOrCreateBasicBlock(l);
addFollower(bb);
}
return pos;
}
