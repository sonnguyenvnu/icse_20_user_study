@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  String serverName=request.getServerName();
  if (!"127.0.0.1".equals(serverName) && !"localhost".equals(serverName)) {
    respond(response,"{ \"code\" : \"error\", \"message\" : \"Workspace directory can only be opened on the local machine where OpenRefine is run.\" }");
  }
 else   if (ProjectManager.singleton instanceof FileProjectManager) {
    File dir=((FileProjectManager)ProjectManager.singleton).getWorkspaceDir();
    if (Desktop.isDesktopSupported()) {
      Desktop desktop=Desktop.getDesktop();
      desktop.open(dir);
    }
 else {
      Runtime.getRuntime().exec("open .",new String[]{},dir);
    }
    respond(response,"{ \"code\" : \"ok\" }");
  }
 else {
    respond(response,"{ \"code\" : \"error\", \"message\" : \"Workspace is not stored on the file system.\" }");
  }
}
