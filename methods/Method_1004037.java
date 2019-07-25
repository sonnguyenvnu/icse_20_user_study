private void process(HttpServletRequest request,HttpServletResponse response) throws IOException {
  String action=StringUtils.EMPTY;
  try {
    action=request.getParameter("action");
  }
 catch (  Exception e) {
    LOGGER.debug(e.toString(),e);
  }
  sendMessage(response,doAction(action));
}
