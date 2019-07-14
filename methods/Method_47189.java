public static void showSMBHelpDialog(Context m,int accentColor){
  MaterialDialog.Builder b=new MaterialDialog.Builder(m);
  b.content(m.getText(R.string.smb_instructions));
  b.positiveText(R.string.doit);
  b.positiveColor(accentColor);
  b.build().show();
}
