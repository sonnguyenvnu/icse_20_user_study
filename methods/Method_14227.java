@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  response.setHeader("Content-Type","application/json");
  try {
    long projectID=Long.parseLong(request.getParameter("project"));
    Map<String,Integer> allProjectTags=ProjectManager.singleton.getAllProjectTags();
    ProjectMetadata metadata=ProjectManager.singleton.getProjectMetadata(projectID);
    for (    String tag : metadata.getTags()) {
      if (allProjectTags.containsKey(tag)) {
        int occurrence=allProjectTags.get(tag);
        if (occurrence == 1)         allProjectTags.remove(tag);
 else {
          allProjectTags.put(tag,occurrence - 1);
        }
      }
    }
    ProjectManager.singleton.deleteProject(projectID);
    respond(response,"{ \"code\" : \"ok\" }");
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
