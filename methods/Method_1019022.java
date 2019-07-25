@Override public JumpInsnNode parse(String text){
  if (matcher.run(text)) {
    return new NamedJumpInsnNode(opcode,matcher.get());
  }
  return fail(text,"Expected: <LABEL_TITLE>");
}
