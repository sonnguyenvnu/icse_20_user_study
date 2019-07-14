private String getUnwrappedResult(){
  return isError() ? error : isInfo() ? info : isSuccess() ? success : null;
}
