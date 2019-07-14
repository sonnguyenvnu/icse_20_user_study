@Override public boolean onContextItemSelected(MenuItem item){
  TerminalSession session=getCurrentTermSession();
switch (item.getItemId()) {
case CONTEXTMENU_SELECT_URL_ID:
    showUrlSelection();
  return true;
case CONTEXTMENU_SHARE_TRANSCRIPT_ID:
if (session != null) {
  Intent intent=new Intent(Intent.ACTION_SEND);
  intent.setType("text/plain");
  intent.putExtra(Intent.EXTRA_TEXT,session.getEmulator().getScreen().getTranscriptText().trim());
  intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.share_transcript_title));
  startActivity(Intent.createChooser(intent,getString(R.string.share_transcript_chooser_title)));
}
return true;
case CONTEXTMENU_PASTE_ID:
doPaste();
return true;
case CONTEXTMENU_KILL_PROCESS_ID:
final AlertDialog.Builder b=new AlertDialog.Builder(this);
b.setIcon(android.R.drawable.ic_dialog_alert);
b.setMessage(R.string.confirm_kill_process);
b.setPositiveButton(android.R.string.yes,(dialog,id) -> {
dialog.dismiss();
getCurrentTermSession().finishIfRunning();
}
);
b.setNegativeButton(android.R.string.no,null);
b.show();
return true;
case CONTEXTMENU_RESET_TERMINAL_ID:
{
if (session != null) {
session.reset();
showToast(getResources().getString(R.string.reset_toast_notification),true);
}
return true;
}
case CONTEXTMENU_STYLING_ID:
{
Intent stylingIntent=new Intent();
stylingIntent.setClassName("com.termux.styling","com.termux.styling.TermuxStyleActivity");
try {
startActivity(stylingIntent);
}
 catch (ActivityNotFoundException|IllegalArgumentException e) {
new AlertDialog.Builder(this).setMessage(R.string.styling_not_installed).setPositiveButton(R.string.styling_install,(dialog,which) -> startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://play.google.com/store/apps/details?id=com.termux.styling")))).setNegativeButton(android.R.string.cancel,null).show();
}
return true;
}
case CONTEXTMENU_HELP_ID:
startActivity(new Intent(this,TermuxHelpActivity.class));
return true;
case CONTEXTMENU_TOGGLE_KEEP_SCREEN_ON:
{
if (mTerminalView.getKeepScreenOn()) {
mTerminalView.setKeepScreenOn(false);
mSettings.setScreenAlwaysOn(this,false);
}
 else {
mTerminalView.setKeepScreenOn(true);
mSettings.setScreenAlwaysOn(this,true);
}
return true;
}
default :
return super.onContextItemSelected(item);
}
}
