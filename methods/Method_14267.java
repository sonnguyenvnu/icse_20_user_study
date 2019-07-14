public Object call(Properties bindings,Object[] args,String mode){
  if (args.length == 1) {
    Object o1=args[0];
    if (o1 != null && o1 instanceof String) {
      if (mode == "html") {
        return Jsoup.parse(o1.toString());
      }
 else       if (mode == "xml") {
        return Jsoup.parse(o1.toString(),"",Parser.xmlParser());
      }
 else {
        return new EvalError(ControlFunctionRegistry.getFunctionName(this) + " unable to identify which parser to use");
      }
    }
  }
  return new EvalError(ControlFunctionRegistry.getFunctionName(this) + " expects a single String as an argument");
}
