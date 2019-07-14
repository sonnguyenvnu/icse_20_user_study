@SneakyThrows protected <T>Consumer<T> createTaskEventListener(ListenerConfig listenerConfig){
  DynamicScriptEngine engine=DynamicScriptEngineFactory.getEngine(listenerConfig.getLanguage());
  if (null != engine) {
    String scriptId=DigestUtils.md5Hex(listenerConfig.getScript());
    if (!engine.compiled(scriptId)) {
      engine.compile(scriptId,listenerConfig.getScript());
    }
    return event -> {
      Map<String,Object> context=new HashMap<>();
      context.put("event",event);
      ExecuteResult result=engine.execute(scriptId,context);
      if (!result.isSuccess()) {
        throw new BusinessException("???????:" + result.getMessage(),result.getException());
      }
    }
;
  }
 else {
    log.warn("????????:{}",listenerConfig.getLanguage());
  }
  return null;
}
