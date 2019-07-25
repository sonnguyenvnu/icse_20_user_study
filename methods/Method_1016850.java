private Pattern[] patternify(String[] regex){
  Pattern[] retval=new Pattern[regex.length];
  for (int i=0; i < regex.length; i++) {
    retval[i]=Pattern.compile(regex[i]);
  }
  return retval;
}
