public void render(HttpServletRequest req,HttpServletResponse resp,Object obj) throws Exception {
  String path=evalPath(req,obj);
  String args="";
  if (path == null)   path="";
 else   if (path.contains("?")) {
    args=path.substring(path.indexOf('?'));
    path=path.substring(0,path.indexOf('?'));
  }
  String ext=getExt();
  if (Strings.isBlank(path)) {
    path=Mvcs.getRequestPath(req);
    path="/WEB-INF" + (path.startsWith("/") ? "" : "/") + Files.renameSuffix(path,ext);
  }
 else   if (path.charAt(0) == '/') {
    if (!path.toLowerCase().endsWith(ext))     path+=ext;
  }
 else {
    path="/WEB-INF/" + path.replace('.','/') + ext;
  }
  path=path + args;
  RequestDispatcher rd=req.getRequestDispatcher(path);
  if (rd == null)   throw Lang.makeThrow("Fail to find Forward '%s'",path);
  rd.forward(req,resp);
}
