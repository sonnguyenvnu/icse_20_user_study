private void responseError(int code,String message,HttpServletResponse response){
  ErrorResponse errorResponse=new ErrorResponse();
  errorResponse.setCode(code);
  errorResponse.setMessage(message);
  response.setStatus(code);
  response.setCharacterEncoding("utf8");
  try {
    response.getOutputStream().write(errorResponse.toString().getBytes(StandardCharsets.UTF_8));
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}
