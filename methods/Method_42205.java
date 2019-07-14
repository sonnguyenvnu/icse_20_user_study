public static AlertDialog getProgressDialog(final ThemedActivity activity,String title,String message){
  AlertDialog.Builder progressDialog=new AlertDialog.Builder(activity,activity.getDialogStyle());
  View dialogLayout=activity.getLayoutInflater().inflate(org.horaapps.leafpic.R.layout.dialog_progress,null);
  TextView dialogTitle=dialogLayout.findViewById(R.id.progress_dialog_title);
  TextView dialogMessage=dialogLayout.findViewById(R.id.progress_dialog_text);
  dialogTitle.setBackgroundColor(activity.getPrimaryColor());
  ((CardView)dialogLayout.findViewById(org.horaapps.leafpic.R.id.progress_dialog_card)).setCardBackgroundColor(activity.getCardBackgroundColor());
  ((ProgressBar)dialogLayout.findViewById(org.horaapps.leafpic.R.id.progress_dialog_loading)).getIndeterminateDrawable().setColorFilter(activity.getPrimaryColor(),android.graphics.PorterDuff.Mode.SRC_ATOP);
  dialogTitle.setText(title);
  dialogMessage.setText(message);
  dialogMessage.setTextColor(activity.getTextColor());
  progressDialog.setCancelable(false);
  progressDialog.setView(dialogLayout);
  return progressDialog.create();
}
