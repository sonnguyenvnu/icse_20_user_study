private static SimpleDialogFragment makeClose(int requestCode,Integer titleId,int messageId,Context context){
  return new Builder(context).setRequestCode(requestCode).setTitle(titleId).setMessage(messageId).setPositiveButtonText(R.string.close).build();
}
