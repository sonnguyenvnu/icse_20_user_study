private static void test7() throws ScriptException {
  NashornScriptEngine engine=createEngine();
  engine.eval("var foo = 23;");
  ScriptContext defaultContext=engine.getContext();
  Bindings defaultBindings=defaultContext.getBindings(ScriptContext.ENGINE_SCOPE);
  SimpleScriptContext context1=new SimpleScriptContext();
  context1.setBindings(defaultBindings,ScriptContext.ENGINE_SCOPE);
  SimpleScriptContext context2=new SimpleScriptContext();
  context2.getBindings(ScriptContext.ENGINE_SCOPE).put("foo",defaultBindings.get("foo"));
  engine.eval("foo = 44;",context1);
  engine.eval("print(foo);",context1);
  engine.eval("print(foo);",context2);
}
