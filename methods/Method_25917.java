private Description describeNoValidConstructorMatch(Tree tree){
  return buildDescription(tree).setMessage("The exception class passed to getChecked must declare a public constructor whose " + "only parameters are of type String or Throwable.").build();
}
