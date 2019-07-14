public boolean hasOption(String option){
  Object op=options.get(option);
  if (op != null && op.equals(true)) {
    return true;
  }
 else {
    return false;
  }
}
