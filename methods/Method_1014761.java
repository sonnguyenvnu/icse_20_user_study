public static AppException build(BindingResult bindingResult){
  StringBuilder sb=new StringBuilder();
  for (  ObjectError e : bindingResult.getAllErrors()) {
    sb.append(e.getDefaultMessage()).append(";");
  }
  return new AppException().setErrorCode(Code.ERROR).setErrorMsg(sb.toString());
}
