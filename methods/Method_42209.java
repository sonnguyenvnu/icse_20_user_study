public static AlertDialog showChangelogDialog(final ThemedActivity activity){
  final AlertDialog.Builder changelogDialogBuilder=new AlertDialog.Builder(activity,activity.getDialogStyle());
  View dialogLayout=activity.getLayoutInflater().inflate(R.layout.dialog_changelog,null);
  TextView dialogTitle=dialogLayout.findViewById(R.id.dialog_changelog_title);
  TextView dialogMessage=dialogLayout.findViewById(R.id.dialog_changelog_text);
  CardView cvBackground=dialogLayout.findViewById(R.id.dialog_changelog_card);
  ScrollView scrChangelog=dialogLayout.findViewById(R.id.changelog_scrollview);
  cvBackground.setCardBackgroundColor(activity.getCardBackgroundColor());
  dialogTitle.setBackgroundColor(activity.getPrimaryColor());
  activity.getThemeHelper().setScrollViewColor(scrChangelog);
  dialogTitle.setText(activity.getString(R.string.changelog));
  Bypass bypass=new Bypass(activity);
  String markdownString;
  try {
    markdownString=getChangeLogFromAssets(activity);
  }
 catch (  IOException e) {
    ChromeCustomTabs.launchUrl(activity,LEAFPIC_CHANGELOG);
    return null;
  }
  CharSequence string=bypass.markdownToSpannable(markdownString);
  dialogMessage.setText(string);
  dialogMessage.setMovementMethod(LinkMovementMethod.getInstance());
  dialogMessage.setTextColor(activity.getTextColor());
  changelogDialogBuilder.setView(dialogLayout);
  changelogDialogBuilder.setPositiveButton(activity.getString(R.string.ok_action).toUpperCase(),null);
  changelogDialogBuilder.setNeutralButton(activity.getString(R.string.show_full).toUpperCase(),new DialogInterface.OnClickListener(){
    @Override public void onClick(    DialogInterface dialog,    int which){
      ChromeCustomTabs.launchUrl(activity,LEAFPIC_CHANGELOG);
    }
  }
);
  return changelogDialogBuilder.show();
}
