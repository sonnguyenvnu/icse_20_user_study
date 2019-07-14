/** 
 * {@link Builder#replace(Tree,String)} 
 */
public static SuggestedFix replace(Tree tree,String replaceWith){
  return builder().replace(tree,replaceWith).build();
}
