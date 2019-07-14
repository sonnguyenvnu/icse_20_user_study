public void setError(String value){
  if (value != null) {
    throw new ExceptionalReturnContentException(value);
  }
}
