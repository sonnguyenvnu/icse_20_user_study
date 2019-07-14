@NonNull public static MessageDialogView newInstance(@NonNull String bundleTitle,@NonNull String bundleMsg,boolean isMarkDown,@Nullable Bundle bundle){
  MessageDialogView messageDialogView=new MessageDialogView();
  messageDialogView.setArguments(getBundle(bundleTitle,bundleMsg,isMarkDown,bundle,false));
  return messageDialogView;
}
