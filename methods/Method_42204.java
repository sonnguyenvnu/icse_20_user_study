public static AlertDialog getTextDialog(ThemedActivity activity,@StringRes int title,@StringRes int Message){
  AlertDialog.Builder builder=new AlertDialog.Builder(activity,activity.getDialogStyle());
  View dialogLayout=activity.getLayoutInflater().inflate(R.layout.dialog_text,null);
  TextView dialogTitle=dialogLayout.findViewById(R.id.text_dialog_title);
  TextView dialogMessage=dialogLayout.findViewById(R.id.text_dialog_message);
  ((CardView)dialogLayout.findViewById(org.horaapps.leafpic.R.id.message_card)).setCardBackgroundColor(activity.getCardBackgroundColor());
  dialogTitle.setBackgroundColor(activity.getPrimaryColor());
  dialogTitle.setText(title);
  dialogMessage.setText(Message);
  dialogMessage.setTextColor(activity.getTextColor());
  builder.setView(dialogLayout);
  return builder.create();
}
