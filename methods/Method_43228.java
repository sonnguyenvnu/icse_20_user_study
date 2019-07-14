@Override public String getMessage(){
  return (error.message == null ? error.name : error.message) + " (HTTP:  " + httpStatusCode + ").";
}
