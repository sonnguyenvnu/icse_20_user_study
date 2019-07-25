@Override public FieldInsnNode parse(String text){
  RegexToken matcher=token();
  MatchResult result=matcher.matches(text);
  if (result.isSuccess()) {
    String owner=matcher.getMatch("OWNER");
    String name=matcher.getMatch("NAME");
    String desc=matcher.getMatch("DESC");
    return new FieldInsnNode(opcode,owner,name,desc);
  }
  return fail(text,"Expected: <HOST>.<NAME> <DESC>",result.getFailedToken().getToken());
}
