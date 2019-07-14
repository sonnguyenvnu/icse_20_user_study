private static NashornScriptEngine createEngine(){
  return (NashornScriptEngine)new ScriptEngineManager().getEngineByName("nashorn");
}
