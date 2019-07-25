/** 
 * ??Snackbar????   sColorWarning
 */
public SnackbarUtils danger(){
  if (getSnackbar() != null) {
    getSnackbar().getView().setBackgroundColor(sColorDanger);
  }
  return this;
}
