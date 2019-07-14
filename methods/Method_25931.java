/** 
 * Converts the tree for the actual value under test (like  {@code actual().foo()}) to a string suitable for passing to  {@code Subject.check(...)} (like {@code "foo()"}, which Truth appends to the name is has for the actual value, producing something like  {@code "bar.foo()"}). <p>Sometimes the tree contains multiple method calls, like  {@code actual().foo().bar()}. In that case, they appear "backward" as we walk the tree (i.e., bar, foo), so we add each one to the beginning of the list as we go.
 */
static String makeCheckDescription(ExpressionTree actual,VisitorState state){
  if (actual.getKind() != METHOD_INVOCATION) {
    return null;
  }
  Deque<String> parts=new ArrayDeque<>();
  MethodInvocationTree invocation=(MethodInvocationTree)actual;
  while (true) {
    ExpressionTree methodSelect=invocation.getMethodSelect();
    if (methodSelect.getKind() != MEMBER_SELECT) {
      return null;
    }
    MemberSelectTree memberSelect=(MemberSelectTree)methodSelect;
    if (!invocation.getArguments().isEmpty()) {
      return null;
    }
    parts.addFirst(memberSelect.getIdentifier() + "()");
    ExpressionTree expression=memberSelect.getExpression();
    if (ACTUAL_METHOD.matches(expression,state) || refersToFieldNamedActual(expression)) {
      return '"' + Joiner.on('.').join(parts) + '"';
    }
    if (expression.getKind() != METHOD_INVOCATION) {
      return null;
    }
    invocation=(MethodInvocationTree)expression;
  }
}
