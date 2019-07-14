/** 
 * Removes the leading comment marker (like  {@code *}) of each line of the comment as well as the start marker ( {@code //},  {@code /*} or {@code /**}and the end markers (<code>&#x2a;/</code>).
 * @param comment the raw comment
 * @return List of lines of the comments
 * @deprecated This method will be removed with PMD 7.0.0.It has been replaced by  {@link Comment#getFilteredComment()}.
 */
@Deprecated public static List<String> multiLinesIn(String comment){
  Token t=new Token();
  t.image=comment;
  MultiLineComment node=new MultiLineComment(t);
  return Arrays.asList(Comment.NEWLINES_PATTERN.split(node.getFilteredComment()));
}
