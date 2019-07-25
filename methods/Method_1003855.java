@Override public void validate(Object target,Errors errors){
  RatpackProperties ratpack=(RatpackProperties)target;
  if (ratpack.contextPath == null) {
    errors.rejectValue("contextPath","context.path.null","Context path cannot be null");
  }
}
