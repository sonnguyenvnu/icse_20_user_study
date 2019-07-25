@Override public String generate(MethodNode method,IincInsnNode insn){
  int index=insn.var;
  LocalVariableNode lvn=InsnUtil.getLocal(method,index);
  String variable=lvn == null ? String.valueOf(index) : name(method,index,lvn.name);
  String operation=insn.incr >= 0 ? "+" : "-";
  int value=Math.abs(insn.incr);
  return OpcodeUtil.opcodeToName(opcode) + " " + variable + " " + operation + " " + value;
}
