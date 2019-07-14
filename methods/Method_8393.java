public static Dialog createFreeSpaceDialog(final LaunchActivity parentActivity){
  final int[] selected=new int[1];
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  int keepMedia=preferences.getInt("keep_media",2);
  if (keepMedia == 2) {
    selected[0]=3;
  }
 else   if (keepMedia == 0) {
    selected[0]=1;
  }
 else   if (keepMedia == 1) {
    selected[0]=2;
  }
 else   if (keepMedia == 3) {
    selected[0]=0;
  }
  String[] descriptions=new String[]{LocaleController.formatPluralString("Days",3),LocaleController.formatPluralString("Weeks",1),LocaleController.formatPluralString("Months",1),LocaleController.getString("LowDiskSpaceNeverRemove",R.string.LowDiskSpaceNeverRemove)};
  final LinearLayout linearLayout=new LinearLayout(parentActivity);
  linearLayout.setOrientation(LinearLayout.VERTICAL);
  TextView titleTextView=new TextView(parentActivity);
  titleTextView.setText(LocaleController.getString("LowDiskSpaceTitle2",R.string.LowDiskSpaceTitle2));
  titleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
  titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
  titleTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  titleTextView.setGravity((LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP);
  linearLayout.addView(titleTextView,LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,24,0,24,8));
  for (int a=0; a < descriptions.length; a++) {
    RadioColorCell cell=new RadioColorCell(parentActivity);
    cell.setPadding(AndroidUtilities.dp(4),0,AndroidUtilities.dp(4),0);
    cell.setTag(a);
    cell.setCheckColor(Theme.getColor(Theme.key_radioBackground),Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
    cell.setTextAndValue(descriptions[a],selected[0] == a);
    linearLayout.addView(cell);
    cell.setOnClickListener(v -> {
      int num=(Integer)v.getTag();
      if (num == 0) {
        selected[0]=3;
      }
 else       if (num == 1) {
        selected[0]=0;
      }
 else       if (num == 2) {
        selected[0]=1;
      }
 else       if (num == 3) {
        selected[0]=2;
      }
      int count=linearLayout.getChildCount();
      for (int a1=0; a1 < count; a1++) {
        View child=linearLayout.getChildAt(a1);
        if (child instanceof RadioColorCell) {
          ((RadioColorCell)child).setChecked(child == v,true);
        }
      }
    }
);
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
  builder.setTitle(LocaleController.getString("LowDiskSpaceTitle",R.string.LowDiskSpaceTitle));
  builder.setMessage(LocaleController.getString("LowDiskSpaceMessage",R.string.LowDiskSpaceMessage));
  builder.setView(linearLayout);
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialog,which) -> MessagesController.getGlobalMainSettings().edit().putInt("keep_media",selected[0]).commit());
  builder.setNeutralButton(LocaleController.getString("ClearMediaCache",R.string.ClearMediaCache),(dialog,which) -> parentActivity.presentFragment(new CacheControlActivity()));
  return builder.create();
}
