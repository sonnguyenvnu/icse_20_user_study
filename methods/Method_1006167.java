/** 
 * Converts all characters of the string to lower case, but does not change words starting with "{"
 */
@Override public String format(String input){
  Title title=new Title(input);
  title.getWords().stream().forEach(Word::toLowerCase);
  return title.toString();
}
