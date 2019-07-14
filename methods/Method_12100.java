private static int calc(String exp){
  try {
    ScriptEngineManager manager=new ScriptEngineManager();
    ScriptEngine engine=manager.getEngineByName("JavaScript");
    Integer catch1=(Integer)engine.eval(exp);
    return catch1.intValue();
  }
 catch (  Exception e) {
    e.printStackTrace();
    return 0;
  }
}
