/** 
 * Shows a message on the screen.
 * @param stringId the string resource id for this message.
 */
public void showMessage(@StringRes Integer stringId){
  if (stringId == null || rootView == null)   return;
  if (snackbar == null) {
    snackbar=Snackbar.make(rootView,stringId,Snackbar.LENGTH_SHORT);
    int tvId=android.support.design.R.id.snackbar_text;
    TextView tv=(TextView)snackbar.getView().findViewById(tvId);
    tv.setTextColor(Color.WHITE);
  }
 else   snackbar.setText(stringId);
  snackbar.show();
}
