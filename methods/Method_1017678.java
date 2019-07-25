/** 
 * Creates a copy of the editor kit.
 * @return a new {@link MultiMarkdownEditorKit} instance
 */
@SuppressWarnings("CloneDoesntCallSuperClone") @Override public Object clone(){
  return new MultiMarkdownEditorKit();
}
