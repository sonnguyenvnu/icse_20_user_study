@Override public IntInsnNode parse(String text){
  if (matcher.run(text))   return new IntInsnNode(opcode,matcher.get());
  return fail(text,"Expected: <VALUE>");
}
