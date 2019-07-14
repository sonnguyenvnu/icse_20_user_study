public static Scope buildInitScope(){
  Scope init=new Scope();
  init.putValue("+",new Add());
  init.putValue("-",new Sub());
  init.putValue("*",new Mult());
  init.putValue("/",new Div());
  init.putValue("<",new Lt());
  init.putValue("<=",new LtE());
  init.putValue(">",new Gt());
  init.putValue(">=",new GtE());
  init.putValue("=",new Eq());
  init.putValue("and",new And());
  init.putValue("or",new Or());
  init.putValue("not",new Not());
  init.putValue("true",new BoolValue(true));
  init.putValue("false",new BoolValue(false));
  init.putValue("Int",Type.INT);
  init.putValue("Bool",Type.BOOL);
  init.putValue("String",Type.STRING);
  return init;
}
