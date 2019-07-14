/** 
 * Applies the appropriate space policy to the given text element.
 * @param in The text element to which the policy should be applied.
 * @return The result of applying the policy to the text element.
 */
static String applyTextElementSpacePolicy(String in){
  String out=in.replaceAll("\r\n","\n");
  out=out.replaceAll(" *\n *","\n");
  out=out.replaceAll("\n"," ");
  out=out.replaceAll("[ \t\\x0B\f\r]+"," ");
  return out;
}
