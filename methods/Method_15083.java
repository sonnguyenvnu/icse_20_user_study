/** 
 * ????short toast?????long toast?? Toast.makeText(string, Toast.LENGTH_LONG).show(); ---???????????
 * @param string
 * @param isForceDismissProgressDialog
 */
public void showShortToast(String string,boolean isForceDismissProgressDialog){
  if (isAlive() == false) {
    Log.w(TAG,"showProgressDialog  isAlive() == false >> return;");
    return;
  }
  context.showShortToast(string,isForceDismissProgressDialog);
}
