private String getType(){
  return isError() ? "Error" : isInfo() ? "Info" : isSuccess() ? "Success" : "<Unknown>";
}
