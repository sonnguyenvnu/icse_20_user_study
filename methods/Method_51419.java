public void checkNumber(T number){
  String error=valueErrorFor(number);
  if (error != null) {
    throw new IllegalArgumentException(error);
  }
}
