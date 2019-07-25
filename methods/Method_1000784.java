@Override protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
  if (!Mvcs.DISABLE_X_POWERED_BY)   resp.setHeader("X-Powered-By",Mvcs.X_POWERED_BY);
  String markKey="nutz_ctx_mark";
  Integer mark=(Integer)req.getAttribute(markKey);
  if (mark != null) {
    req.setAttribute(markKey,mark + 1);
  }
 else {
    req.setAttribute(markKey,0);
  }
  ServletContext prCtx=Mvcs.getServletContext();
  Mvcs.setServletContext(sc);
  String preName=Mvcs.getName();
  Context preContext=Mvcs.resetALL();
  try {
    if (sp != null)     req=sp.filter(req,resp,sc);
    Mvcs.set(selfName,req,resp);
    if (!handler.handle(req,resp))     resp.sendError(404);
  }
  finally {
    Mvcs.resetALL();
    if (mark != null) {
      Mvcs.setServletContext(prCtx);
      Mvcs.set(preName,req,resp);
      Mvcs.ctx().reqCtx(preContext);
      if (mark == 0) {
        req.removeAttribute(markKey);
      }
 else {
        req.setAttribute(markKey,mark - 1);
      }
    }
 else {
      Mvcs.setServletContext(null);
      Mvcs.ctx().removeReqCtx();
    }
  }
}
