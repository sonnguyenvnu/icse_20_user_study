/** 
 * Returns a fix that adds a  {@code @SuppressWarnings(warningToSuppress)} to the closestsuppressible element to the node pointed at by  {@code state.getPath()}.
 * @see #addSuppressWarnings(VisitorState,String,String)
 */
@Nullable public static Fix addSuppressWarnings(VisitorState state,String warningToSuppress){
  return addSuppressWarnings(state,warningToSuppress,null);
}
