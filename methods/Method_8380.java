public static AlertDialog showSecretLocationAlert(Context context,int currentAccount,final Runnable onSelectRunnable,boolean inChat){
  ArrayList<String> arrayList=new ArrayList<>();
  int providers=MessagesController.getInstance(currentAccount).availableMapProviders;
  if ((providers & 1) != 0) {
    arrayList.add(LocaleController.getString("MapPreviewProviderTelegram",R.string.MapPreviewProviderTelegram));
  }
  if ((providers & 2) != 0) {
    arrayList.add(LocaleController.getString("MapPreviewProviderGoogle",R.string.MapPreviewProviderGoogle));
  }
  if ((providers & 4) != 0) {
    arrayList.add(LocaleController.getString("MapPreviewProviderYandex",R.string.MapPreviewProviderYandex));
  }
  arrayList.add(LocaleController.getString("MapPreviewProviderNobody",R.string.MapPreviewProviderNobody));
  AlertDialog.Builder builder=new AlertDialog.Builder(context).setTitle(LocaleController.getString("ChooseMapPreviewProvider",R.string.ChooseMapPreviewProvider)).setItems(arrayList.toArray(new String[0]),(dialog,which) -> {
    SharedConfig.setSecretMapPreviewType(which);
    if (onSelectRunnable != null) {
      onSelectRunnable.run();
    }
  }
);
  if (!inChat) {
    builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
  }
  AlertDialog dialog=builder.show();
  if (inChat) {
    dialog.setCanceledOnTouchOutside(false);
  }
  return dialog;
}
