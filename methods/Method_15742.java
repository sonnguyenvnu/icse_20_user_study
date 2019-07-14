protected void handleExpression(Authentication authentication,AuthorizeDefinition definition,MethodInterceptorContext paramContext){
  if (definition.getScript() != null) {
    String scriptId=DigestUtils.md5Hex(definition.getScript().getScript());
    DynamicScriptEngine engine=DynamicScriptEngineFactory.getEngine(definition.getScript().getLanguage());
    if (null == engine) {
      throw new AccessDenyException("{unknown_engine}:" + definition.getScript().getLanguage());
    }
    if (!engine.compiled(scriptId)) {
      try {
        engine.compile(scriptId,definition.getScript().getScript());
      }
 catch (      Exception e) {
        logger.error("express compile error",e);
        throw new AccessDenyException("{expression_error}");
      }
    }
    Map<String,Object> var=new HashMap<>(paramContext.getParams());
    var.put("auth",authentication);
    Object success=engine.execute(scriptId,var).get();
    if (!(success instanceof Boolean) || !((Boolean)success)) {
      throw new AccessDenyException(definition.getMessage());
    }
  }
}
