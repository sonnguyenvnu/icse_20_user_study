public ParseException error(final Exception cause){
  return new ParseException(cause.getMessage(),cause,line,col,lineEnd,colEnd);
}
