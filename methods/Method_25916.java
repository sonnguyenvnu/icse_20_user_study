private Description describeUncheckedExceptionTypeMatch(Tree tree,Fix fix){
  return buildDescription(tree).setMessage("The exception class passed to getChecked must be a checked exception, " + "not a RuntimeException.").addFix(fix).build();
}
