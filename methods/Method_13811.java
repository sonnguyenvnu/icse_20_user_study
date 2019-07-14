@Override public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  if (logger.isDebugEnabled()) {
    logger.debug("SavedConnectionCommand::Get::connectionName::{}",request.getParameter("connectionName"));
  }
  String connectionName=request.getParameter("connectionName");
  try {
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type","application/json");
    if (connectionName == null || connectionName.isEmpty()) {
      writeSavedConnectionResponse(response);
    }
 else {
      DatabaseConfiguration savedConnection=DatabaseUtils.getSavedConnection(connectionName);
      writeSavedConnectionResponse(response,savedConnection);
    }
  }
 catch (  Exception e) {
    logger.error("Exception while loading settings {}",e);
  }
}
