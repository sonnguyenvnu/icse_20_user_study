/** 
 * ??mSnackbar????  sColorConfirm
 */
public SnackbarUtils confirm(){
  if (getSnackbar() != null) {
    getSnackbar().getView().setBackgroundColor(sColorConfirm);
  }
  return this;
}
