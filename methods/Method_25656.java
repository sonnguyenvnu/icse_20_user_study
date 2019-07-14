@Override public Matcher<? super ExpressionTree> specializedMatcher(){
  return instanceMethod().onExactClass("android.graphics.Rect").named("intersect");
}
