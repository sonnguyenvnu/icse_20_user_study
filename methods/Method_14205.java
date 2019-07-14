@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    String expression=request.getParameter("expression");
    ((TopList)ProjectManager.singleton.getPreferenceStore().get("scripting.expressions")).add(expression);
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type","application/json");
    response.getWriter().write("{ \"code\" : \"ok\" }");
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
