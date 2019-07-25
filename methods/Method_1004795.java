private FieldType mapping(String fieldMapping,Parser parser){
  FieldType esType=esMapping.get(fieldMapping);
  if (esType != null) {
    return esType;
  }
  Token currentToken=parser.currentToken();
  if (!currentToken.isValue()) {
    return FieldType.OBJECT;
  }
  if (inMetadataSection) {
    return FieldType.STRING;
  }
switch (currentToken) {
case VALUE_NULL:
    esType=FieldType.NULL;
  break;
case VALUE_BOOLEAN:
esType=FieldType.BOOLEAN;
break;
case VALUE_STRING:
esType=FieldType.STRING;
break;
case VALUE_NUMBER:
NumberType numberType=parser.numberType();
switch (numberType) {
case INT:
esType=FieldType.INTEGER;
break;
case LONG:
esType=FieldType.LONG;
break;
case FLOAT:
esType=FieldType.FLOAT;
break;
case DOUBLE:
esType=FieldType.DOUBLE;
break;
case BIG_DECIMAL:
throw new UnsupportedOperationException();
case BIG_INTEGER:
throw new UnsupportedOperationException();
default :
break;
}
break;
default :
break;
}
return esType;
}
