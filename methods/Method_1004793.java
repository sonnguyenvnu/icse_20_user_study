private static void traverse(Parser parser,Generator generator){
  Token t=parser.currentToken();
switch (t) {
case START_OBJECT:
    traverseMap(parser,generator);
  break;
case START_ARRAY:
traverseArray(parser,generator);
break;
case FIELD_NAME:
generator.writeFieldName(parser.currentName());
parser.nextToken();
traverse(parser,generator);
break;
case VALUE_STRING:
generator.writeString(parser.text());
parser.nextToken();
break;
case VALUE_BOOLEAN:
generator.writeBoolean(parser.booleanValue());
parser.nextToken();
break;
case VALUE_NULL:
generator.writeNull();
parser.nextToken();
break;
case VALUE_NUMBER:
switch (parser.numberType()) {
case INT:
generator.writeNumber(parser.intValue());
break;
case LONG:
generator.writeNumber(parser.longValue());
break;
case DOUBLE:
generator.writeNumber(parser.doubleValue());
break;
case FLOAT:
generator.writeNumber(parser.floatValue());
break;
}
parser.nextToken();
break;
}
}
