/** 
 * Returns a list of indices of javadoc tag occurrences in the comment. <p>Note: if the same tag occurs multiple times, only the last occurrence is returned.
 * @param comments the complete comment text
 * @return list of indices.
 * @deprecated This method is deprecated and will be removed with PMD 7.0.0.It is not very useful, since it doesn't extract the information in a useful way. You would still need check, which tags have been found, and with which data they might be accompanied. A more useful solution will be added around the AST node  {@link FormalComment}, which contains as children  {@link JavadocElement} nodes, which inturn provide access to the  {@link JavadocTag}.
 */
@Deprecated protected List<Integer> tagsIndicesIn(String comments){
  Map<String,Integer> tags=CommentUtil.javadocTagsIn(comments);
  return new ArrayList<>(tags.values());
}
