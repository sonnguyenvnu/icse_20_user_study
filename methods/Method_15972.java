protected <T>void tryValidateProperty(org.hswebframework.web.service.Validator<T> validator,String property,T value,String message){
  if (validator != null) {
    if (!validator.validate(value)) {
      throw new ValidationException(message,property);
    }
  }
}
