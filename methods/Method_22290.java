/** 
 * Build the dialog from the values in config
 * @param savedInstanceState old state to restore
 */
protected void buildAndShowDialog(@Nullable Bundle savedInstanceState){
  final AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(this);
  final String title=dialogConfiguration.title();
  if (title != null) {
    dialogBuilder.setTitle(title);
  }
  final int iconResourceId=dialogConfiguration.resIcon();
  if (iconResourceId != ACRAConstants.DEFAULT_RES_VALUE) {
    dialogBuilder.setIcon(iconResourceId);
  }
  dialogBuilder.setView(buildCustomView(savedInstanceState)).setPositiveButton(dialogConfiguration.positiveButtonText(),this).setNegativeButton(dialogConfiguration.negativeButtonText(),this);
  mDialog=dialogBuilder.create();
  mDialog.setCanceledOnTouchOutside(false);
  mDialog.show();
}
