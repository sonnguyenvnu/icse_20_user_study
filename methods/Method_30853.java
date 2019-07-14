private static SimpleDialogFragment makeYesNo(int requestCode,Integer titleId,int messageId,Context context){
  return new Builder(context).setRequestCode(requestCode).setTitle(titleId).setMessage(messageId).setPositiveButtonText(R.string.yes).setNegativeButtonText(R.string.no).build();
}
