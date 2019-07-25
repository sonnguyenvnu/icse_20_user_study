/** 
 * Converts all characters of the given string to upper case, but does not change words starting with "{"
 */
@Override public String format(String input){
  Title title=new Title(input);
  title.getWords().stream().forEach(Word::toUpperCase);
  return title.toString();
}
