/** 
 * Converts the first character of each word of the given string to a upper case (and all others to lower case), but does not change words starting with "{"
 */
@Override public String format(String input){
  Title title=new Title(input);
  title.getWords().stream().forEach(Word::toUpperFirst);
  return title.toString();
}
