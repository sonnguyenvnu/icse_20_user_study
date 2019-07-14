/** 
 * @param isGetting
 */
private void showVerifyGet(final boolean isGetting){
  pbPasswordGettingVerify.setVisibility(isGetting ? View.VISIBLE : View.GONE);
  btnPasswordGetVerify.setText(isGetting ? "???..." : "????");
}
