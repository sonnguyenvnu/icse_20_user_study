@SuppressWarnings("rawtypes") @Override public void parseField(DefaultJSONParser parser,Object object,Type objectType,Map<String,Object> fieldValues){
  JSONLexer lexer=parser.lexer;
  final int token=lexer.token();
  if (token == JSONToken.NULL || (token == JSONToken.LITERAL_STRING && lexer.stringVal().length() == 0)) {
    setValue(object,null);
    return;
  }
  ArrayList list=new ArrayList();
  ParseContext context=parser.getContext();
  parser.setContext(context,object,fieldInfo.name);
  parseArray(parser,objectType,list);
  parser.setContext(context);
  if (object == null) {
    fieldValues.put(fieldInfo.name,list);
  }
 else {
    setValue(object,list);
  }
}
