private static void test8() throws ScriptException {
  NashornScriptEngine engine=createEngine();
  engine.eval("var obj = { foo: 23 };");
  ScriptContext defaultContext=engine.getContext();
  Bindings defaultBindings=defaultContext.getBindings(ScriptContext.ENGINE_SCOPE);
  SimpleScriptContext context1=new SimpleScriptContext();
  context1.setBindings(defaultBindings,ScriptContext.ENGINE_SCOPE);
  SimpleScriptContext context2=new SimpleScriptContext();
  context2.getBindings(ScriptContext.ENGINE_SCOPE).put("obj",defaultBindings.get("obj"));
  engine.eval("obj.foo = 44;",context1);
  engine.eval("print(obj.foo);",context1);
  engine.eval("print(obj.foo);",context2);
}
