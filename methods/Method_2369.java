@Override public boolean validate(ValidatorContext context,String s){
  if (null == s || s.length() > max || s.length() < min) {
    context.addError(ValidationError.create(String.format("%s??????%s~%s???",fieldName,min,max)).setErrorCode(-1).setField(fieldName).setInvalidValue(s));
    return false;
  }
  return true;
}
