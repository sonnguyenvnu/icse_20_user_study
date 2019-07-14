private void showPayAlert(final String totalPrice){
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  builder.setTitle(LocaleController.getString("PaymentTransactionReview",R.string.PaymentTransactionReview));
  builder.setMessage(LocaleController.formatString("PaymentTransactionMessage",R.string.PaymentTransactionMessage,totalPrice,currentBotName,currentItemName));
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
    setDonePressed(true);
    sendData();
  }
);
  builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
  showDialog(builder.create());
}
