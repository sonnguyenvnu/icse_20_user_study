@Override public List<ValidationError> validate(){
  List<ValidationError> errors=new ArrayList<>();
  if (getUdn() == null) {
    errors.add(new ValidationError(getClass(),"major","Device has no UDN"));
  }
  return errors;
}
