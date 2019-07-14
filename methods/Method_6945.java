private static void showCantOpenAlert(BaseFragment fragment,String reason){
  if (fragment == null || fragment.getParentActivity() == null) {
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(fragment.getParentActivity());
  builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
  builder.setMessage(reason);
  fragment.showDialog(builder.create());
}
