@ExceptionHandler @ResponseBody public String handleException(IllegalStateException ex){
  return "Handled exception: " + ex.getMessage();
}
