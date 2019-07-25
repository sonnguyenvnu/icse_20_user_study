private static void write(HttpServletResponse writer,String message){
  try {
    writer.getWriter().write(message + "\n");
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}
