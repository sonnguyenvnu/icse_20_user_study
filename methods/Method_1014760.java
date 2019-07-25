public static AppException build(String errorMsg){
  return new AppException().setErrorCode(Code.ERROR).setErrorMsg(errorMsg);
}
