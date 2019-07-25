@Override public String generate(MethodNode method,TableSwitchInsnNode insn){
  List<String> offsets=new ArrayList<>();
  for (int i=0; i < insn.labels.size(); i++)   offsets.add(InsnUtil.labelName(insn.labels.get(i)));
  String range=insn.min + "-" + insn.max;
  String dflt=InsnUtil.labelName(insn.dflt);
  return OpcodeUtil.opcodeToName(opcode) + " range[" + range + "] offsets[" + String.join(",",offsets) + "] default[" + dflt + "]";
}
