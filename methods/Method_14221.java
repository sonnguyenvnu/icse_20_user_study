@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  String modname=request.getParameter("module");
  if (modname == null) {
    modname="core";
  }
  String[] langs=request.getParameterValues("lang");
  if (langs == null || "".equals(langs[0])) {
    PreferenceStore ps=ProjectManager.singleton.getPreferenceStore();
    if (ps != null) {
      langs=new String[]{(String)ps.get("userLang")};
    }
  }
  langs=Arrays.copyOf(langs,langs.length + 1);
  langs[langs.length - 1]="en";
  ObjectNode json=null;
  boolean loaded=false;
  for (  String lang : langs) {
    if (lang == null)     continue;
    json=loadLanguage(this.servlet,modname,lang);
    if (json != null) {
      response.setCharacterEncoding("UTF-8");
      response.setContentType("application/json");
      try {
        ObjectNode node=ParsingUtilities.mapper.createObjectNode();
        node.put("dictionary",json);
        node.put("lang",new TextNode(lang));
        ParsingUtilities.mapper.writeValue(response.getWriter(),node);
      }
 catch (      IOException e) {
        logger.error("Error writing language labels to response stream");
      }
      response.getWriter().flush();
      response.getWriter().close();
      loaded=true;
      break;
    }
  }
  if (!loaded) {
    logger.error("Failed to load any language files");
  }
}
