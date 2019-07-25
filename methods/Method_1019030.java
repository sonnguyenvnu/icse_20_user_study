@Override public String generate(MethodNode method,LookupSwitchInsnNode insn){
  List<String> keys=new ArrayList<>();
  for (int i=0; i < insn.keys.size(); i++)   keys.add(insn.keys.get(i) + "=" + InsnUtil.labelName(insn.labels.get(i)));
  String dflt=InsnUtil.labelName(insn.dflt);
  return OpcodeUtil.opcodeToName(opcode) + " mapping[" + String.join(",",keys) + "] default[" + dflt + "]";
}
