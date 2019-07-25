public void error(Exception e) throws IOException {
  if (e instanceof RecordNotFoundException) {
    status=HttpStatus.HttpStatusNotFound;
  }
 else   if (e instanceof RecordExistedException || e instanceof ArgumentErrorException || e instanceof JSONException) {
    status=HttpStatus.HttpStatusBadRequest;
  }
 else {
    status=HttpStatus.HttpStatusSystemError;
  }
  httpServletResponse.setContentType("text/plain;charset=UTF-8");
  httpServletResponse.setStatus(status);
  output(e.getMessage());
}
