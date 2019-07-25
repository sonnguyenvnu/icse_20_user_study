@Override public String generate(MethodNode method,FieldInsnNode insn){
  String host=insn.owner;
  String name=insn.name;
  String desc=insn.desc;
  return OpcodeUtil.opcodeToName(opcode) + " " + host + "." + name + " " + desc;
}
