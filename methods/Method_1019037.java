@Override public String generate(MethodNode method,VarInsnNode insn){
  int index=insn.var;
  LocalVariableNode lvn=InsnUtil.getLocal(method,index);
  String variable=null;
  if (lvn == null) {
    if (index == 0 && !AccessFlag.isStatic(method.access)) {
      variable="this";
    }
 else {
      variable=String.valueOf(index);
    }
  }
 else {
    variable=name(method,index,lvn.name);
  }
  return OpcodeUtil.opcodeToName(opcode) + " " + variable;
}
