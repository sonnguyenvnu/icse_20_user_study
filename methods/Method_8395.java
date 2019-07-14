public static Dialog createSingleChoiceDialog(Activity parentActivity,final String[] options,final String title,final int selected,final DialogInterface.OnClickListener listener){
  final LinearLayout linearLayout=new LinearLayout(parentActivity);
  linearLayout.setOrientation(LinearLayout.VERTICAL);
  AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
  for (int a=0; a < options.length; a++) {
    RadioColorCell cell=new RadioColorCell(parentActivity);
    cell.setPadding(AndroidUtilities.dp(4),0,AndroidUtilities.dp(4),0);
    cell.setTag(a);
    cell.setCheckColor(Theme.getColor(Theme.key_radioBackground),Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
    cell.setTextAndValue(options[a],selected == a);
    linearLayout.addView(cell);
    cell.setOnClickListener(v -> {
      int sel=(Integer)v.getTag();
      builder.getDismissRunnable().run();
      listener.onClick(null,sel);
    }
);
  }
  builder.setTitle(title);
  builder.setView(linearLayout);
  builder.setPositiveButton(LocaleController.getString("Cancel",R.string.Cancel),null);
  return builder.create();
}
