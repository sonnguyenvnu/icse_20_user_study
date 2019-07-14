/** 
 * Create a VisitorState object from a compilation unit. 
 */
private VisitorState createVisitorState(Context context,DescriptionListener listener){
  ErrorProneOptions options=requireNonNull(context.get(ErrorProneOptions.class));
  return VisitorState.createConfiguredForCompilation(context,listener,scanner().severityMap(),options);
}
