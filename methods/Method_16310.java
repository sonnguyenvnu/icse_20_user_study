@Override public Optional<String> parse(String target,Object context){
  Map<String,Object> vars=new HashMap<>();
  vars.put("context",context);
  DynamicScriptEngine engine=DynamicScriptEngineFactory.getEngine(language);
  String scriptId=String.valueOf(script.hashCode());
  try {
    if (!engine.compiled(scriptId)) {
      engine.compile(scriptId,language);
    }
    Object result=engine.execute(scriptId,vars).getIfSuccess();
    if (result == null) {
      return Optional.empty();
    }
    return Optional.of(String.valueOf(result));
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
