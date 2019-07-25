@Override public MultiANewArrayInsnNode parse(String text){
  RegexToken matcher=token();
  MatchResult result=matcher.matches(text);
  if (result.isSuccess()) {
    String type=matcher.getMatch("TYPE");
    int dimensions=matcher.getMatch("DIMENSION");
    return new MultiANewArrayInsnNode(type,dimensions);
  }
  return fail(text,"Expected: <TYPE> <DIMENSION>",result.getFailedToken().getToken());
}
