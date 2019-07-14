protected Map<String,Trigger> buildTrigger(String config){
  if (StringUtils.isEmpty(config)) {
    return new HashMap<>();
  }
  JSONArray triggerConfig=JSON.parseArray(config);
  Map<String,Trigger> triggers=new HashMap<>();
  for (int i=0; i < triggerConfig.size(); i++) {
    JSONObject single=triggerConfig.getJSONObject(i);
    String trigger=single.getString("trigger");
    String language=single.getString("language");
    String script=single.getString("script");
    String scriptId=DigestUtils.md5Hex(script);
    try {
      DynamicScriptEngine engine=DynamicScriptEngineFactory.getEngine(language);
      if (engine == null) {
        throw new UnsupportedOperationException("not support script language : " + language);
      }
      if (!engine.compiled(scriptId)) {
        engine.compile(scriptId,script);
      }
      Trigger singleTrigger=new ScriptTraggerSupport(engine,scriptId);
      triggers.put(trigger,singleTrigger);
    }
 catch (    Exception e) {
      throw new BusinessException("compile script error :" + e.getMessage(),e);
    }
  }
  return triggers;
}
