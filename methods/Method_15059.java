/** 
 * ??????
 */
public void dismissProgressDialog(){
  runUiThread(new Runnable(){
    @Override public void run(){
      if (progressDialog != null && progressDialog.isShowing()) {
        progressDialog.dismiss();
      }
      if (pbBaseTitle != null) {
        pbBaseTitle.setVisibility(View.GONE);
      }
    }
  }
);
}
