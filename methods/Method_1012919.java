@Override public void send(HttpServletRequest req,HttpServletResponse resp) throws IOException {
  req.setAttribute(Const.ParamsNames.ERROR,Boolean.toString(isError));
  addStatusMessagesToPageData(req);
  if (isClearingStatusMessage) {
    clearStatusMessageForRequest(req);
  }
  resp.setContentType("application/json");
  resp.setCharacterEncoding("UTF-8");
  String jsonData=JsonUtils.toJson(data);
  resp.getWriter().write(jsonData);
}
