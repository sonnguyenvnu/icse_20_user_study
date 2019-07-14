public static void showRateAlert(final Context context,final Runnable onDismiss,final long callID,final long accessHash,final int account,final boolean userInitiative){
  final File log=getLogFile(callID);
  final int[] page={0};
  LinearLayout alertView=new LinearLayout(context);
  alertView.setOrientation(LinearLayout.VERTICAL);
  int pad=AndroidUtilities.dp(16);
  alertView.setPadding(pad,pad,pad,0);
  TextView text=new TextView(context);
  text.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
  text.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
  text.setGravity(Gravity.CENTER);
  text.setText(LocaleController.getString("VoipRateCallAlert",R.string.VoipRateCallAlert));
  alertView.addView(text);
  final BetterRatingView bar=new BetterRatingView(context);
  alertView.addView(bar,LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.CENTER_HORIZONTAL,0,16,0,0));
  final LinearLayout problemsWrap=new LinearLayout(context);
  problemsWrap.setOrientation(LinearLayout.VERTICAL);
  View.OnClickListener problemCheckboxClickListener=new View.OnClickListener(){
    @Override public void onClick(    View v){
      CheckBoxCell check=(CheckBoxCell)v;
      check.setChecked(!check.isChecked(),true);
    }
  }
;
  final String[] problems={"echo","noise","interruptions","distorted_speech","silent_local","silent_remote","dropped"};
  for (int i=0; i < problems.length; i++) {
    CheckBoxCell check=new CheckBoxCell(context,1);
    check.setClipToPadding(false);
    check.setTag(problems[i]);
    String label=null;
switch (i) {
case 0:
      label=LocaleController.getString("RateCallEcho",R.string.RateCallEcho);
    break;
case 1:
  label=LocaleController.getString("RateCallNoise",R.string.RateCallNoise);
break;
case 2:
label=LocaleController.getString("RateCallInterruptions",R.string.RateCallInterruptions);
break;
case 3:
label=LocaleController.getString("RateCallDistorted",R.string.RateCallDistorted);
break;
case 4:
label=LocaleController.getString("RateCallSilentLocal",R.string.RateCallSilentLocal);
break;
case 5:
label=LocaleController.getString("RateCallSilentRemote",R.string.RateCallSilentRemote);
break;
case 6:
label=LocaleController.getString("RateCallDropped",R.string.RateCallDropped);
break;
}
check.setText(label,null,false,false);
check.setOnClickListener(problemCheckboxClickListener);
check.setTag(problems[i]);
problemsWrap.addView(check);
}
alertView.addView(problemsWrap,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,-8,0,-8,0));
problemsWrap.setVisibility(View.GONE);
final EditText commentBox=new EditText(context);
commentBox.setHint(LocaleController.getString("VoipFeedbackCommentHint",R.string.VoipFeedbackCommentHint));
commentBox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
commentBox.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
commentBox.setHintTextColor(Theme.getColor(Theme.key_dialogTextHint));
commentBox.setBackgroundDrawable(Theme.createEditTextDrawable(context,true));
commentBox.setPadding(0,AndroidUtilities.dp(4),0,AndroidUtilities.dp(4));
commentBox.setTextSize(18);
commentBox.setVisibility(View.GONE);
alertView.addView(commentBox,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,8,8,8,0));
final boolean[] includeLogs={true};
final CheckBoxCell checkbox=new CheckBoxCell(context,1);
View.OnClickListener checkClickListener=new View.OnClickListener(){
@Override public void onClick(View v){
includeLogs[0]=!includeLogs[0];
checkbox.setChecked(includeLogs[0],true);
}
}
;
checkbox.setText(LocaleController.getString("CallReportIncludeLogs",R.string.CallReportIncludeLogs),null,true,false);
checkbox.setClipToPadding(false);
checkbox.setOnClickListener(checkClickListener);
alertView.addView(checkbox,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,-8,0,-8,0));
final TextView logsText=new TextView(context);
logsText.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
logsText.setTextColor(Theme.getColor(Theme.key_dialogTextGray3));
logsText.setText(LocaleController.getString("CallReportLogsExplain",R.string.CallReportLogsExplain));
logsText.setPadding(AndroidUtilities.dp(8),0,AndroidUtilities.dp(8),0);
logsText.setOnClickListener(checkClickListener);
alertView.addView(logsText);
checkbox.setVisibility(View.GONE);
logsText.setVisibility(View.GONE);
if (!log.exists()) {
includeLogs[0]=false;
}
final AlertDialog alert=new AlertDialog.Builder(context).setTitle(LocaleController.getString("CallMessageReportProblem",R.string.CallMessageReportProblem)).setView(alertView).setPositiveButton(LocaleController.getString("Send",R.string.Send),new DialogInterface.OnClickListener(){
@Override public void onClick(DialogInterface dialog,int which){
}
}
).setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null).setOnDismissListener(new DialogInterface.OnDismissListener(){
@Override public void onDismiss(DialogInterface dialog){
if (onDismiss != null) onDismiss.run();
}
}
).create();
if (BuildVars.DEBUG_VERSION && log.exists()) {
alert.setNeutralButton("Send log",new DialogInterface.OnClickListener(){
@Override public void onClick(DialogInterface dialog,int which){
Intent intent=new Intent(context,LaunchActivity.class);
intent.setAction(Intent.ACTION_SEND);
intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(log));
context.startActivity(intent);
}
}
);
}
alert.show();
alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
final View btn=alert.getButton(DialogInterface.BUTTON_POSITIVE);
btn.setEnabled(false);
bar.setOnRatingChangeListener(new BetterRatingView.OnRatingChangeListener(){
@Override public void onRatingChanged(int rating){
btn.setEnabled(rating > 0);
((TextView)btn).setText((rating < 4 ? LocaleController.getString("Next",R.string.Next) : LocaleController.getString("Send",R.string.Send)).toUpperCase());
}
}
);
btn.setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View v){
int rating=bar.getRating();
if (rating >= 4 || page[0] == 1) {
final int currentAccount=UserConfig.selectedAccount;
final TLRPC.TL_phone_setCallRating req=new TLRPC.TL_phone_setCallRating();
req.rating=bar.getRating();
ArrayList<String> problemTags=new ArrayList<>();
for (int i=0; i < problemsWrap.getChildCount(); i++) {
CheckBoxCell check=(CheckBoxCell)problemsWrap.getChildAt(i);
if (check.isChecked()) problemTags.add("#" + check.getTag());
}
if (req.rating < 5) req.comment=commentBox.getText().toString();
 else req.comment="";
if (!problemTags.isEmpty() && !includeLogs[0]) {
req.comment+=" " + TextUtils.join(" ",problemTags);
}
req.peer=new TLRPC.TL_inputPhoneCall();
req.peer.access_hash=accessHash;
req.peer.id=callID;
req.user_initiative=userInitiative;
ConnectionsManager.getInstance(account).sendRequest(req,new RequestDelegate(){
@Override public void run(TLObject response,TLRPC.TL_error error){
if (response instanceof TLRPC.TL_updates) {
TLRPC.TL_updates updates=(TLRPC.TL_updates)response;
MessagesController.getInstance(currentAccount).processUpdates(updates,false);
}
if (includeLogs[0] && log.exists() && req.rating < 4) {
SendMessagesHelper.prepareSendingDocument(log.getAbsolutePath(),log.getAbsolutePath(),null,TextUtils.join(" ",problemTags),"text/plain",VOIP_SUPPORT_ID,null,null,null);
Toast.makeText(context,LocaleController.getString("CallReportSent",R.string.CallReportSent),Toast.LENGTH_LONG).show();
}
}
}
);
alert.dismiss();
}
 else {
page[0]=1;
bar.setVisibility(View.GONE);
text.setVisibility(View.GONE);
alert.setTitle(LocaleController.getString("CallReportHint",R.string.CallReportHint));
commentBox.setVisibility(View.VISIBLE);
if (log.exists()) {
checkbox.setVisibility(View.VISIBLE);
logsText.setVisibility(View.VISIBLE);
}
problemsWrap.setVisibility(View.VISIBLE);
((TextView)btn).setText(LocaleController.getString("Send",R.string.Send).toUpperCase());
}
}
}
);
}
