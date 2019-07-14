@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  Project project=request.getParameter("project") != null ? getProject(request) : null;
  PreferenceStore ps=project != null ? project.getMetadata().getPreferenceStore() : ProjectManager.singleton.getPreferenceStore();
  String prefName=request.getParameter("name");
  String valueString=request.getParameter("value");
  try {
    JsonNode o=valueString == null ? null : ParsingUtilities.mapper.readTree(valueString);
    ps.put(prefName,PreferenceStore.loadObject(o));
    respond(response,"{ \"code\" : \"ok\" }");
  }
 catch (  IOException e) {
    respondException(response,e);
  }
}
