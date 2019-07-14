private void execute(HttpServletRequest request,HttpServletResponse response){
  try {
    TimeUnit.SECONDS.sleep(2);
  }
 catch (  InterruptedException e) {
    e.printStackTrace();
  }
  try {
    response.getWriter().append("hello");
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}
