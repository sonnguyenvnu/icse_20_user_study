@Override public VarInsnNode parse(String text){
  if (matcher.run(text))   return new NamedVarInsnNode(opcode,matcher.get());
  return fail(text,"Expected: <VARIABLE>");
}
