/** 
 * Does the actual work of detaching. Non-test subclasses should NOT override. Use onDetach for custom code.
 */
protected void doDetach(){
  mDraweeHolder.onDetach();
}
