/** 
 * Produce a string in double quotes with backslash sequences in all the right places. A backslash will be inserted within &lt;/, producing &lt;\/, allowing JSON text to be delivered in HTML. In JSON text, a string cannot contain a control character or an unescaped quote or backslash.
 * @param string A String
 * @return A String correctly formatted for insertion in a JSON text.
 */
public static String quote(String string){
  StringWriter sw=new StringWriter();
synchronized (sw.getBuffer()) {
    try {
      return quote(string,sw).toString();
    }
 catch (    IOException ignored) {
      return "";
    }
  }
}
