protected void tryValidateProperty(boolean success,String property,String message){
  if (!success) {
    throw new ValidationException(message,property);
  }
}
