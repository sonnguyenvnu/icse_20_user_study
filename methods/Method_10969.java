/** 
 * ??mChildOfContent????
 * @return
 */
private int computeUsableHeight(){
  Rect r=new Rect();
  mChildOfContent.getWindowVisibleDisplayFrame(r);
  return (r.bottom - r.top);
}
