/** 
 * Parses the given string.
 * @param string the string
 * @throws SerializationException if the string cannot be successfully parsed. 
 */
public void parse(String string){
  char[] data=string.toCharArray();
  parse(data,0,data.length);
}
