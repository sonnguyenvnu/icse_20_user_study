@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    Project project=getProject(request);
    ProjectManager.singleton.ensureProjectSaved(project.id);
    response.setHeader("Content-Type","application/x-gzip");
    OutputStream os=response.getOutputStream();
    try {
      FileProjectManager.gzipTarToOutputStream(project,os);
    }
  finally {
      os.close();
    }
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
