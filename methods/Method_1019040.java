/** 
 * Completes fields.
 * @param token tokenizer root node
 * @param part current token to complete on
 * @return a list of field completions, ordered alphabetically
 */
public static List<String> field(RegexToken token,String part){
  return matchSignatures(token,part,c -> Arrays.stream(c.getDeclaredFields()).map(fd -> fd.getName() + " " + Type.getType(fd.getType())),cn -> cn.fields.stream().map(fn -> fn.name + " " + fn.desc));
}
