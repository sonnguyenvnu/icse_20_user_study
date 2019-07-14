/** 
 * Detects all unicode emojis in input string and replaces them with the return value of transformer.transform()
 * @param input the string to process
 * @param transformer emoji transformer to apply to each emoji
 * @return input string with all emojis transformed
 */
private static String parseFromUnicode(String input,EmojiTransformer transformer){
  int prev=0;
  StringBuilder sb=new StringBuilder();
  List<UnicodeCandidate> replacements=getUnicodeCandidates(input);
  for (  UnicodeCandidate candidate : replacements) {
    sb.append(input.substring(prev,candidate.getEmojiStartIndex()));
    sb.append(transformer.transform(candidate));
    prev=candidate.getFitzpatrickEndIndex();
  }
  return sb.append(input.substring(prev)).toString();
}
