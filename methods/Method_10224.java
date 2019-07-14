private static void test2() throws ScriptException {
  NashornScriptEngine engine=createEngine();
  engine.eval("function foo() { print('bar') };");
  SimpleScriptContext context=new SimpleScriptContext();
  engine.eval("print(Function);",context);
  engine.eval("foo();",context);
}
