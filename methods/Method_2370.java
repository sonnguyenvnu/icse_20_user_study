@Override public boolean validate(ValidatorContext context,String s){
  if (null == s) {
    context.addError(ValidationError.create(String.format("%s?????",fieldName)).setErrorCode(-1).setField(fieldName).setInvalidValue(s));
    return false;
  }
  return true;
}
