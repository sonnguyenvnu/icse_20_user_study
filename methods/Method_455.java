public void parseExtra(Object object,String key){
  final JSONLexer lexer=this.lexer;
  lexer.nextTokenWithColon();
  Type type=null;
  if (extraTypeProviders != null) {
    for (    ExtraTypeProvider extraProvider : extraTypeProviders) {
      type=extraProvider.getExtraType(object,key);
    }
  }
  Object value=type == null ? parse() : parseObject(type);
  if (object instanceof ExtraProcessable) {
    ExtraProcessable extraProcessable=((ExtraProcessable)object);
    extraProcessable.processExtra(key,value);
    return;
  }
  if (extraProcessors != null) {
    for (    ExtraProcessor process : extraProcessors) {
      process.processExtra(object,key,value);
    }
  }
  if (resolveStatus == NeedToResolve) {
    resolveStatus=NONE;
  }
}
