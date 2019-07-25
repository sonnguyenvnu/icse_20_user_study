private void validate(String newValue){
  for (  PropertyChangeValidator v : validators) {
    try {
      v.validate(newValue);
    }
 catch (    ValidationException e) {
      throw e;
    }
catch (    Throwable e) {
      throw new ValidationException("Unexpected exception during validation",e);
    }
  }
}
