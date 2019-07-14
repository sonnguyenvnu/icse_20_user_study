public void parseObject(Object object){
  Class<?> clazz=object.getClass();
  JavaBeanDeserializer beanDeser=null;
  ObjectDeserializer deserizer=config.getDeserializer(clazz);
  if (deserizer instanceof JavaBeanDeserializer) {
    beanDeser=(JavaBeanDeserializer)deserizer;
  }
  if (lexer.token() != JSONToken.LBRACE && lexer.token() != JSONToken.COMMA) {
    throw new JSONException("syntax error, expect {, actual " + lexer.tokenName());
  }
  for (; ; ) {
    String key=lexer.scanSymbol(symbolTable);
    if (key == null) {
      if (lexer.token() == JSONToken.RBRACE) {
        lexer.nextToken(JSONToken.COMMA);
        break;
      }
      if (lexer.token() == JSONToken.COMMA) {
        if (lexer.isEnabled(Feature.AllowArbitraryCommas)) {
          continue;
        }
      }
    }
    FieldDeserializer fieldDeser=null;
    if (beanDeser != null) {
      fieldDeser=beanDeser.getFieldDeserializer(key);
    }
    if (fieldDeser == null) {
      if (!lexer.isEnabled(Feature.IgnoreNotMatch)) {
        throw new JSONException("setter not found, class " + clazz.getName() + ", property " + key);
      }
      lexer.nextTokenWithColon();
      parse();
      if (lexer.token() == JSONToken.RBRACE) {
        lexer.nextToken();
        return;
      }
      continue;
    }
 else {
      Class<?> fieldClass=fieldDeser.fieldInfo.fieldClass;
      Type fieldType=fieldDeser.fieldInfo.fieldType;
      Object fieldValue;
      if (fieldClass == int.class) {
        lexer.nextTokenWithColon(JSONToken.LITERAL_INT);
        fieldValue=IntegerCodec.instance.deserialze(this,fieldType,null);
      }
 else       if (fieldClass == String.class) {
        lexer.nextTokenWithColon(JSONToken.LITERAL_STRING);
        fieldValue=StringCodec.deserialze(this);
      }
 else       if (fieldClass == long.class) {
        lexer.nextTokenWithColon(JSONToken.LITERAL_INT);
        fieldValue=LongCodec.instance.deserialze(this,fieldType,null);
      }
 else {
        ObjectDeserializer fieldValueDeserializer=config.getDeserializer(fieldClass,fieldType);
        lexer.nextTokenWithColon(fieldValueDeserializer.getFastMatchToken());
        fieldValue=fieldValueDeserializer.deserialze(this,fieldType,null);
      }
      fieldDeser.setValue(object,fieldValue);
    }
    if (lexer.token() == JSONToken.COMMA) {
      continue;
    }
    if (lexer.token() == JSONToken.RBRACE) {
      lexer.nextToken(JSONToken.COMMA);
      return;
    }
  }
}
