@Override public AutoCompletionInput analyze(String input){
  return determinePrefixAndReturnRemainder(input,getDelimiter());
}
