/** 
 * ???????
 * @param context
 * @param hint ???
 */
public static void loadingHttp(Context context,String hint){
  if (mDialogLoading != null) {
    mDialogLoading.cancel();
  }
  mDialogLoading=new RxDialogLoading(context);
  mDialogLoading.setCanceledOnTouchOutside(false);
  mDialogLoading.setCancelable(false);
  mDialogLoading.setLoadingText(hint);
  mDialogLoading.setLoadingColor(ContextCompat.getColor(context,R.color.lightseagreen));
  if (!mDialogLoading.isShowing()) {
    mDialogLoading.show();
  }
}
