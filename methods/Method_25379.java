public static Matcher<MethodInvocationTree> argumentCount(final int argumentCount){
  return (t,state) -> t.getArguments().size() == argumentCount;
}
