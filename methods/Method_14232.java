protected void internalRespond(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  Project project=null;
  String importingJobID=request.getParameter("importingJobID");
  if (importingJobID != null) {
    long jobID=Long.parseLong(importingJobID);
    ImportingJob job=ImportingManager.getJob(jobID);
    if (job != null) {
      project=job.project;
    }
  }
  if (project == null) {
    project=getProject(request);
  }
  response.setHeader("Cache-Control","no-cache");
  Map<String,LanguageInfo> prefixesMap=new HashMap<>();
  for (  String languagePrefix : MetaParser.getLanguagePrefixes()) {
    LanguageInfo info=MetaParser.getLanguageInfo(languagePrefix);
    prefixesMap.put(languagePrefix,info);
  }
  Map<String,HttpHeaderInfo> headersMap=new HashMap<>();
  for (  String headerLabel : HttpHeadersSupport.getHttpHeaderLabels()) {
    HttpHeaderInfo info=HttpHeadersSupport.getHttpHeaderInfo(headerLabel);
    headersMap.put(headerLabel,info);
  }
  respondJSON(response,new ModelsResponse(project.columnModel,project.recordModel,project.overlayModels,prefixesMap,headersMap));
}
