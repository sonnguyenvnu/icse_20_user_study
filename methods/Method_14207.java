@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  Project project=request.getParameter("project") != null ? getProject(request) : null;
  PreferenceStore ps=project != null ? project.getMetadata().getPreferenceStore() : ProjectManager.singleton.getPreferenceStore();
  Map<String,Object> map=new HashMap<>();
  for (  String key : ps.getKeys()) {
    Object pref=ps.get(key);
    if (pref == null || pref instanceof String || pref instanceof Number || pref instanceof Boolean) {
      map.put(key,pref);
    }
  }
  respondJSON(response,map);
}
