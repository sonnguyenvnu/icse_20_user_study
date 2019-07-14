/** 
 * Finds all the javadoc tags in the (formal) comment. Returns a map from javadoc tag to index position. <p>Note: If a tag is used multiple times, the last occurrence is returned.
 * @param comment the raw comment
 * @return mapping of javadoc tag to index position
 * @deprecated This method is deprecated and will be removed with PMD 7.0.0.This method has been intended to parse javadoc tags. A more useful solution will be added around the AST node  {@link FormalComment}, which contains as children  {@link JavadocElement} nodes, which inturn provide access to the  {@link JavadocTag}.
 */
@Deprecated public static Map<String,Integer> javadocTagsIn(String comment){
  Map<String,Integer> tags=new HashMap<>();
  if (comment != null) {
    Matcher m=JAVADOC_TAG.matcher(comment);
    while (m.find()) {
      String match=comment.substring(m.start() + 1,m.end());
      tags.put(match,m.start());
    }
  }
  return tags;
}
