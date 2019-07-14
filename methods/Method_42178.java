private void showErrors(CompositeException exceptions){
  ArrayList<ErrorCause> errors=new ArrayList<>(exceptions.size());
  for (  Throwable throwable : exceptions.getExceptions())   errors.add(ErrorCause.fromThrowable(throwable));
  showErrors(errors);
}
