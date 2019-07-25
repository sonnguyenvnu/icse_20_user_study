/** 
 * Converts the first character of the first word of the given string to upper case (and the remaining characters of the first word to lower case) and changes other words to lower case, but does not change anything if word starts with "{"
 */
@Override public String format(String input){
  Title title=new Title(new LowerCaseFormatter().format(input));
  title.getWords().stream().findFirst().ifPresent(Word::toUpperFirst);
  return title.toString();
}
