@Override public void showMessage(@NonNull String titleRes,@NonNull String msgRes){
  hideProgress();
  if (toast != null)   toast.cancel();
  Context context=App.getInstance();
  toast=titleRes.equals(context.getString(R.string.error)) ? Toasty.error(context,msgRes,Toast.LENGTH_LONG) : Toasty.info(context,msgRes,Toast.LENGTH_LONG);
  toast.show();
}
