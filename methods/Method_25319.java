/** 
 * Modifies  {@code fixBuilder} to either create a new {@code @SuppressWarnings} element on theclosest suppressible node, or add  {@code warningToSuppress} to that node if there's already a{@code SuppressWarnings} annotation there.
 * @see #addSuppressWarnings(VisitorState,String,String)
 */
public static void addSuppressWarnings(Builder fixBuilder,VisitorState state,String warningToSuppress){
  addSuppressWarnings(fixBuilder,state,warningToSuppress,null);
}
