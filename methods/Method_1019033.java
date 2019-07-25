@Override public String generate(MethodNode method,MultiANewArrayInsnNode insn){
  return OpcodeUtil.opcodeToName(opcode) + " " + insn.desc + " " + insn.dims;
}
