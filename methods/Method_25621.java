/** 
 * Produce a MockForbidder to use when looking for disallowed mocks, in addition to the built-in checks for Annotations of type  {@code T}. <p>This method will be called at most once for each instance of AbstractMockChecker, but of course the returned object's  {@link MockForbidder#forbidReason(Type,VisitorState) forbidReason} method may be called many times.<p>The default implementation forbids nothing.
 */
protected MockForbidder forbidder(){
  return (type,state) -> Optional.empty();
}
