@Override public String generate(MethodNode method,TypeInsnNode insn){
  return OpcodeUtil.opcodeToName(opcode) + " " + insn.desc;
}
