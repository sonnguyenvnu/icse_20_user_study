@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  response.setHeader("Content-Type","application/json");
  Project project;
  try {
    project=getProject(request);
  }
 catch (  ServletException e) {
    respond(response,"error",e.getLocalizedMessage());
    return;
  }
  ProjectMetadata metadata=project.getMetadata();
  String oldT=request.getParameter("old");
  String newT=request.getParameter("new");
  Map<String,Integer> allProjectTags=ProjectManager.singleton.getAllProjectTags();
  String[] oldTags=oldT.split(",");
  for (  String tag : oldTags) {
    if (allProjectTags != null && allProjectTags.containsKey(tag)) {
      int occurrence=allProjectTags.get(tag);
      if (occurrence == 1) {
        allProjectTags.remove(tag);
      }
 else {
        allProjectTags.put(tag,occurrence - 1);
      }
    }
  }
  String[] newTags=newT.split(" |\\,");
  List<String> polishedTags=new ArrayList<String>(newTags.length);
  for (  String tag : newTags) {
    tag=tag.trim();
    if (!tag.isEmpty()) {
      if (allProjectTags != null && allProjectTags.containsKey(tag)) {
        allProjectTags.put(tag,allProjectTags.get(tag) + 1);
      }
 else {
        allProjectTags.put(tag,1);
      }
      polishedTags.add(tag);
    }
  }
  metadata.setTags(polishedTags.toArray(new String[polishedTags.size()]));
  metadata.updateModified();
  respond(response,"{ \"code\" : \"ok\" }");
}
