public void hideProgressDialog(){
  if (mProgressDialog != null && mProgressDialog.isShowing()) {
    mProgressDialog.dismiss();
  }
}
