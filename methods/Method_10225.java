private static void test1() throws ScriptException {
  NashornScriptEngine engine=createEngine();
  engine.eval("function foo() { print('bar') };");
  engine.eval("foo();");
}
