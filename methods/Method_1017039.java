public ParseException error(final String message){
  return new ParseException(message,null,line,col,lineEnd,colEnd);
}
