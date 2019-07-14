/** 
 * If not explicitly specified, this view and its children don't support autofill. <p> This is done because autofill's means of uniquely identifying views doesn't work out of the box with View recycling.
 */
@SuppressLint("InlinedApi") private void initAutofill(){
  if (ViewCompat.getImportantForAutofill(this) == View.IMPORTANT_FOR_AUTOFILL_AUTO) {
    ViewCompat.setImportantForAutofill(this,View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS);
  }
}
