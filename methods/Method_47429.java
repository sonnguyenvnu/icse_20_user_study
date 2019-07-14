private void showPaymentsDialog(final BasicActivity context){
  final MaterialDialog.Builder builder=new MaterialDialog.Builder(context);
  builder.title(R.string.donate);
  builder.adapter(this,null);
  builder.theme(context.getAppTheme().getMaterialDialogTheme());
  builder.cancelListener(dialog -> purchaseProduct.purchaseCancel());
  builder.show();
}
