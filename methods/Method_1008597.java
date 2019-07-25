/** 
 * Compiles a script using the given context.
 * @return a compiled script which may be used to construct instances of a script for the given context
 */
public <FactoryType>FactoryType compile(Script script,ScriptContext<FactoryType> context){
  Objects.requireNonNull(script);
  Objects.requireNonNull(context);
  ScriptType type=script.getType();
  String lang=script.getLang();
  String idOrCode=script.getIdOrCode();
  Map<String,String> options=script.getOptions();
  String id=idOrCode;
  if (type == ScriptType.STORED) {
    StoredScriptSource source=getScriptFromClusterState(id);
    lang=source.getLang();
    idOrCode=source.getSource();
    options=source.getOptions();
  }
  boolean expression="expression".equals(lang);
  boolean notSupported=context.name.equals(ExecutableScript.UPDATE_CONTEXT.name);
  if (expression && notSupported) {
    throw new UnsupportedOperationException("scripts of type [" + script.getType() + "]," + " operation [" + context.name + "] and lang [" + lang + "] are not supported");
  }
  ScriptEngine scriptEngine=getEngine(lang);
  if (isTypeEnabled(type) == false) {
    throw new IllegalArgumentException("cannot execute [" + type + "] scripts");
  }
  if (contexts.containsKey(context.name) == false) {
    throw new IllegalArgumentException("script context [" + context.name + "] not supported");
  }
  if (isContextEnabled(context) == false) {
    throw new IllegalArgumentException("cannot execute scripts using [" + context.name + "] context");
  }
  if (logger.isTraceEnabled()) {
    logger.trace("compiling lang: [{}] type: [{}] script: {}",lang,type,idOrCode);
  }
  CacheKey cacheKey=new CacheKey(lang,idOrCode,context.name,options);
  Object compiledScript=cache.get(cacheKey);
  if (compiledScript != null) {
    return context.factoryClazz.cast(compiledScript);
  }
synchronized (this) {
    compiledScript=cache.get(cacheKey);
    if (compiledScript == null) {
      try {
        if (logger.isTraceEnabled()) {
          logger.trace("compiling script, type: [{}], lang: [{}], options: [{}]",type,lang,options);
        }
        checkCompilationLimit();
        compiledScript=scriptEngine.compile(id,idOrCode,context,options);
      }
 catch (      ScriptException good) {
        throw good;
      }
catch (      Exception exception) {
        throw new GeneralScriptException("Failed to compile " + type + " script [" + id + "] using lang [" + lang + "]",exception);
      }
      scriptMetrics.onCompilation();
      cache.put(cacheKey,compiledScript);
    }
    return context.factoryClazz.cast(compiledScript);
  }
}
