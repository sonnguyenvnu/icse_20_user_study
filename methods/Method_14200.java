protected void respondWithErrorPage(HttpServletRequest request,HttpServletResponse response,String message,Throwable e){
  VelocityContext context=new VelocityContext();
  context.put("message",message);
  if (e != null) {
    StringWriter writer=new StringWriter();
    e.printStackTrace(new PrintWriter(writer));
    context.put("stack",writer.toString());
  }
 else {
    context.put("stack","");
  }
  try {
    servlet.getModule("core").sendTextFromTemplate(request,response,context,"error.vt","UTF-8","text/html",true);
  }
 catch (  Exception e1) {
    e1.printStackTrace();
  }
}
