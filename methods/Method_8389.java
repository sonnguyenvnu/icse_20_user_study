public static void showSendMediaAlert(int result,final BaseFragment fragment){
  if (result == 0) {
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(fragment.getParentActivity());
  builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
  if (result == 1) {
    builder.setMessage(LocaleController.getString("ErrorSendRestrictedStickers",R.string.ErrorSendRestrictedStickers));
  }
 else   if (result == 2) {
    builder.setMessage(LocaleController.getString("ErrorSendRestrictedMedia",R.string.ErrorSendRestrictedMedia));
  }
 else   if (result == 3) {
    builder.setMessage(LocaleController.getString("ErrorSendRestrictedPolls",R.string.ErrorSendRestrictedPolls));
  }
 else   if (result == 4) {
    builder.setMessage(LocaleController.getString("ErrorSendRestrictedStickersAll",R.string.ErrorSendRestrictedStickersAll));
  }
 else   if (result == 5) {
    builder.setMessage(LocaleController.getString("ErrorSendRestrictedMediaAll",R.string.ErrorSendRestrictedMediaAll));
  }
 else   if (result == 6) {
    builder.setMessage(LocaleController.getString("ErrorSendRestrictedPollsAll",R.string.ErrorSendRestrictedPollsAll));
  }
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
  fragment.showDialog(builder.create(),true,null);
}
