@Override public String generate(MethodNode method,LdcInsnNode insn){
  Object value=insn.cst;
  String s=value.toString();
  if (value instanceof String) {
    s="\"" + StringEscapeUtils.escapeJava(s) + "\"";
  }
 else   if (value instanceof Float) {
    s+="f";
  }
 else   if (value instanceof Double) {
    s+="d";
  }
 else   if (value instanceof Long) {
    s+="l";
  }
  return OpcodeUtil.opcodeToName(opcode) + " " + s;
}
