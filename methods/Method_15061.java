/** 
 * ????short toast?????long toast?? Toast.makeText(string, Toast.LENGTH_LONG).show(); ---???????????
 * @param string
 * @param isForceDismissProgressDialog
 */
public void showShortToast(final String string,final boolean isForceDismissProgressDialog){
  runUiThread(new Runnable(){
    @Override public void run(){
      if (isForceDismissProgressDialog) {
        dismissProgressDialog();
      }
      Toast.makeText(context,"" + string,Toast.LENGTH_SHORT).show();
    }
  }
);
}
