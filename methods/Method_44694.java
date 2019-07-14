public void checkResult(){
  if (!result) {
    OkexException e=new OkexException();
    e.setCode(errorCode);
    e.setMessage(errorMessage);
    throw e;
  }
}
