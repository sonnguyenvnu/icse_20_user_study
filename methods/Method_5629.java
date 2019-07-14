private static String parsePropertyValue(ParsableByteArray input,StringBuilder stringBuilder){
  StringBuilder expressionBuilder=new StringBuilder();
  String token;
  int position;
  boolean expressionEndFound=false;
  while (!expressionEndFound) {
    position=input.getPosition();
    token=parseNextToken(input,stringBuilder);
    if (token == null) {
      return null;
    }
    if (BLOCK_END.equals(token) || ";".equals(token)) {
      input.setPosition(position);
      expressionEndFound=true;
    }
 else {
      expressionBuilder.append(token);
    }
  }
  return expressionBuilder.toString();
}
