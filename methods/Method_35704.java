public Error first(){
  if (errors.isEmpty()) {
    throw new IllegalStateException("No errors are present");
  }
  return errors.get(0);
}
