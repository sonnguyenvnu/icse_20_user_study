@Override public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  if (request.getPathInfo().startsWith("/command/")) {
    String commandKey=getCommandKey(request);
    Command command=commands.get(commandKey);
    if (command != null) {
      if (request.getMethod().equals("GET")) {
        if (!logger.isTraceEnabled() && command.logRequests()) {
          logger.info("GET {}",request.getPathInfo());
        }
        logger.trace("> GET {}",commandKey);
        command.doGet(request,response);
        logger.trace("< GET {}",commandKey);
      }
 else       if (request.getMethod().equals("POST")) {
        if (!logger.isTraceEnabled() && command.logRequests()) {
          logger.info("POST {}",request.getPathInfo());
        }
        logger.trace("> POST {}",commandKey);
        command.doPost(request,response);
        logger.trace("< POST {}",commandKey);
      }
 else       if (request.getMethod().equals("PUT")) {
        if (!logger.isTraceEnabled() && command.logRequests()) {
          logger.info("PUT {}",request.getPathInfo());
        }
        logger.trace("> PUT {}",commandKey);
        command.doPut(request,response);
        logger.trace("< PUT {}",commandKey);
      }
 else       if (request.getMethod().equals("DELETE")) {
        if (!logger.isTraceEnabled() && command.logRequests()) {
          logger.info("DELETE {}",request.getPathInfo());
        }
        logger.trace("> DELETE {}",commandKey);
        command.doDelete(request,response);
        logger.trace("< DELETE {}",commandKey);
      }
 else {
        response.sendError(405);
      }
    }
 else {
      response.sendError(404);
    }
  }
 else {
    super.service(request,response);
  }
}
