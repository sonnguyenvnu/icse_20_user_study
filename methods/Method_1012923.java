@Override public void send(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
  addStatusMessagesToPageData(req);
  req.setAttribute("data",data);
  req.setAttribute(Const.ParamsNames.ERROR,Boolean.toString(isError));
  req.getRequestDispatcher(getDestinationWithParams()).forward(req,resp);
}
