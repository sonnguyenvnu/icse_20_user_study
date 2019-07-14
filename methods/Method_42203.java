public static AlertDialog getInsertTextDialog(ThemedActivity activity,EditText editText,@StringRes int title){
  AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(activity,activity.getDialogStyle());
  View dialogLayout=activity.getLayoutInflater().inflate(org.horaapps.leafpic.R.layout.dialog_insert_text,null);
  TextView textViewTitle=dialogLayout.findViewById(R.id.rename_title);
  ((CardView)dialogLayout.findViewById(org.horaapps.leafpic.R.id.dialog_chose_provider_title)).setCardBackgroundColor(activity.getCardBackgroundColor());
  textViewTitle.setBackgroundColor(activity.getPrimaryColor());
  textViewTitle.setText(title);
  ThemeHelper.setCursorColor(editText,activity.getTextColor());
  FrameLayout.LayoutParams layoutParams=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
  editText.setLayoutParams(layoutParams);
  editText.setSingleLine(true);
  editText.getBackground().mutate().setColorFilter(activity.getTextColor(),PorterDuff.Mode.SRC_IN);
  editText.setTextColor(activity.getTextColor());
  try {
    Field f=TextView.class.getDeclaredField("mCursorDrawableRes");
    f.setAccessible(true);
    f.set(editText,null);
  }
 catch (  Exception ignored) {
  }
  ((RelativeLayout)dialogLayout.findViewById(org.horaapps.leafpic.R.id.container_edit_text)).addView(editText);
  dialogBuilder.setView(dialogLayout);
  return dialogBuilder.create();
}
