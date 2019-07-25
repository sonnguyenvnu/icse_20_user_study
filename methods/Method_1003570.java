private void validate(){
  if (!isValid()) {
    throw new IllegalStateException("This session is invalid.");
  }
}
