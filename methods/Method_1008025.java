static SearchRequest convert(SearchTemplateRequest searchTemplateRequest,SearchTemplateResponse response,ScriptService scriptService,NamedXContentRegistry xContentRegistry) throws IOException {
  Script script=new Script(searchTemplateRequest.getScriptType(),searchTemplateRequest.getScriptType() == ScriptType.STORED ? null : TEMPLATE_LANG,searchTemplateRequest.getScript(),searchTemplateRequest.getScriptParams() == null ? Collections.emptyMap() : searchTemplateRequest.getScriptParams());
  TemplateScript compiledScript=scriptService.compile(script,TemplateScript.CONTEXT).newInstance(script.getParams());
  String source=compiledScript.execute();
  response.setSource(new BytesArray(source));
  SearchRequest searchRequest=searchTemplateRequest.getRequest();
  if (searchTemplateRequest.isSimulate()) {
    return null;
  }
  try (XContentParser parser=XContentFactory.xContent(XContentType.JSON).createParser(xContentRegistry,source)){
    SearchSourceBuilder builder=SearchSourceBuilder.searchSource();
    builder.parseXContent(parser);
    builder.explain(searchTemplateRequest.isExplain());
    builder.profile(searchTemplateRequest.isProfile());
    searchRequest.source(builder);
  }
   return searchRequest;
}
