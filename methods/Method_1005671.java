/** 
 * Start defining instructions for the named label.
 */
public void mark(Label label){
  adopt(label);
  if (label.marked) {
    throw new IllegalStateException("already marked");
  }
  label.marked=true;
  if (currentLabel != null) {
    jump(label);
  }
  currentLabel=label;
}
