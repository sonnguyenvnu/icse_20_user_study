public static ResponseDefinition notPermitted(String message){
  return notPermitted(Errors.single(40,message));
}
