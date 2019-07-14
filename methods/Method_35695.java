public static ClientError fromErrors(Errors errors){
  Integer errorCode=errors.first().getCode();
switch (errorCode) {
case 10:
    return new InvalidInputException(errors);
case 30:
  return new NotRecordingException();
case 50:
return new NotPermittedException(errors);
default :
return new ClientError(errors);
}
}
