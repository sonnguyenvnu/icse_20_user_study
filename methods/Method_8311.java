private void processSelectedOption(int option){
  if (selectedObject == null || getParentActivity() == null) {
    return;
  }
switch (option) {
case 0:
{
      if (selectedObjectGroup != null) {
        boolean success=true;
        for (int a=0; a < selectedObjectGroup.messages.size(); a++) {
          if (!SendMessagesHelper.getInstance(currentAccount).retrySendMessage(selectedObjectGroup.messages.get(a),false)) {
            success=false;
          }
        }
        if (success) {
          moveScrollToLastMessage();
        }
      }
 else {
        if (SendMessagesHelper.getInstance(currentAccount).retrySendMessage(selectedObject,false)) {
          updateVisibleRows();
          moveScrollToLastMessage();
        }
      }
      break;
    }
case 1:
{
    if (getParentActivity() == null) {
      selectedObject=null;
      selectedObjectGroup=null;
      return;
    }
    createDeleteMessagesAlert(selectedObject,selectedObjectGroup);
    break;
  }
case 2:
{
  forwardingMessage=selectedObject;
  forwardingMessageGroup=selectedObjectGroup;
  Bundle args=new Bundle();
  args.putBoolean("onlySelect",true);
  args.putInt("dialogsType",3);
  DialogsActivity fragment=new DialogsActivity(args);
  fragment.setDelegate(this);
  presentFragment(fragment);
  break;
}
case 3:
{
AndroidUtilities.addToClipboard(getMessageContent(selectedObject,0,false));
break;
}
case 4:
{
if (Build.VERSION.SDK_INT >= 23 && getParentActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
getParentActivity().requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},4);
selectedObject=null;
selectedObjectGroup=null;
return;
}
if (selectedObjectGroup != null) {
for (int a=0; a < selectedObjectGroup.messages.size(); a++) {
  saveMessageToGallery(selectedObjectGroup.messages.get(a));
}
}
 else {
saveMessageToGallery(selectedObject);
}
break;
}
case 5:
{
File locFile=null;
if (!TextUtils.isEmpty(selectedObject.messageOwner.attachPath)) {
File f=new File(selectedObject.messageOwner.attachPath);
if (f.exists()) {
locFile=f;
}
}
if (locFile == null) {
File f=FileLoader.getPathToMessage(selectedObject.messageOwner);
if (f.exists()) {
locFile=f;
}
}
if (locFile != null) {
if (locFile.getName().toLowerCase().endsWith("attheme")) {
if (chatLayoutManager != null) {
  int lastPosition=chatLayoutManager.findFirstVisibleItemPosition();
  if (lastPosition != 0) {
    scrollToPositionOnRecreate=lastPosition;
    RecyclerListView.Holder holder=(RecyclerListView.Holder)chatListView.findViewHolderForAdapterPosition(scrollToPositionOnRecreate);
    if (holder != null) {
      scrollToOffsetOnRecreate=chatListView.getMeasuredHeight() - holder.itemView.getBottom() - chatListView.getPaddingBottom();
    }
 else {
      scrollToPositionOnRecreate=-1;
    }
  }
 else {
    scrollToPositionOnRecreate=-1;
  }
}
Theme.ThemeInfo themeInfo=Theme.applyThemeFile(locFile,selectedObject.getDocumentName(),true);
if (themeInfo != null) {
  presentFragment(new ThemePreviewActivity(locFile,themeInfo));
}
 else {
  scrollToPositionOnRecreate=-1;
  if (getParentActivity() == null) {
    selectedObject=null;
    selectedObjectGroup=null;
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
  builder.setMessage(LocaleController.getString("IncorrectTheme",R.string.IncorrectTheme));
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
  showDialog(builder.create());
}
}
 else {
if (LocaleController.getInstance().applyLanguageFile(locFile,currentAccount)) {
  presentFragment(new LanguageSelectActivity());
}
 else {
  if (getParentActivity() == null) {
    selectedObject=null;
    selectedObjectGroup=null;
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
  builder.setMessage(LocaleController.getString("IncorrectLocalization",R.string.IncorrectLocalization));
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
  showDialog(builder.create());
}
}
}
break;
}
case 6:
{
String path=selectedObject.messageOwner.attachPath;
if (path != null && path.length() > 0) {
File temp=new File(path);
if (!temp.exists()) {
path=null;
}
}
if (path == null || path.length() == 0) {
path=FileLoader.getPathToMessage(selectedObject.messageOwner).toString();
}
Intent intent=new Intent(Intent.ACTION_SEND);
intent.setType(selectedObject.getDocument().mime_type);
File f=new File(path);
if (Build.VERSION.SDK_INT >= 24) {
try {
intent.putExtra(Intent.EXTRA_STREAM,FileProvider.getUriForFile(getParentActivity(),BuildConfig.APPLICATION_ID + ".provider",f));
intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
}
 catch (Exception ignore) {
intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(f));
}
}
 else {
intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(f));
}
try {
getParentActivity().startActivityForResult(Intent.createChooser(intent,LocaleController.getString("ShareFile",R.string.ShareFile)),500);
}
 catch (Throwable ignore) {
}
break;
}
case 7:
{
String path=selectedObject.messageOwner.attachPath;
if (path != null && path.length() > 0) {
File temp=new File(path);
if (!temp.exists()) {
path=null;
}
}
if (path == null || path.length() == 0) {
path=FileLoader.getPathToMessage(selectedObject.messageOwner).toString();
}
if (Build.VERSION.SDK_INT >= 23 && getParentActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
getParentActivity().requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},4);
selectedObject=null;
selectedObjectGroup=null;
return;
}
MediaController.saveFile(path,getParentActivity(),0,null,null);
break;
}
case 8:
{
showFieldPanelForReply(selectedObject);
break;
}
case 9:
{
showDialog(new StickersAlert(getParentActivity(),this,selectedObject.getInputStickerSet(),null,bottomOverlayChat.getVisibility() != View.VISIBLE && (currentChat == null || ChatObject.canSendStickers(currentChat)) ? chatActivityEnterView : null));
break;
}
case 10:
{
if (Build.VERSION.SDK_INT >= 23 && getParentActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
getParentActivity().requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},4);
selectedObject=null;
selectedObjectGroup=null;
return;
}
String fileName=FileLoader.getDocumentFileName(selectedObject.getDocument());
if (TextUtils.isEmpty(fileName)) {
fileName=selectedObject.getFileName();
}
String path=selectedObject.messageOwner.attachPath;
if (path != null && path.length() > 0) {
File temp=new File(path);
if (!temp.exists()) {
path=null;
}
}
if (path == null || path.length() == 0) {
path=FileLoader.getPathToMessage(selectedObject.messageOwner).toString();
}
MediaController.saveFile(path,getParentActivity(),selectedObject.isMusic() ? 3 : 2,fileName,selectedObject.getDocument() != null ? selectedObject.getDocument().mime_type : "");
break;
}
case 11:
{
TLRPC.Document document=selectedObject.getDocument();
MessagesController.getInstance(currentAccount).saveGif(selectedObject,document);
showGifHint();
chatActivityEnterView.addRecentGif(document);
break;
}
case 12:
{
startEditingMessageObject(selectedObject);
selectedObject=null;
selectedObjectGroup=null;
break;
}
case 13:
{
final int mid=selectedObject.getId();
AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
builder.setTitle(LocaleController.getString("PinMessageAlertTitle",R.string.PinMessageAlertTitle));
final boolean[] checks;
if (currentUser != null) {
builder.setMessage(LocaleController.getString("PinMessageAlertChat",R.string.PinMessageAlertChat));
checks=new boolean[]{false};
}
 else if (ChatObject.isChannel(currentChat) && currentChat.megagroup || currentChat != null && !ChatObject.isChannel(currentChat)) {
builder.setMessage(LocaleController.getString("PinMessageAlert",R.string.PinMessageAlert));
checks=new boolean[]{true};
FrameLayout frameLayout=new FrameLayout(getParentActivity());
CheckBoxCell cell=new CheckBoxCell(getParentActivity(),1);
cell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
cell.setText(LocaleController.getString("PinNotify",R.string.PinNotify),"",true,false);
cell.setPadding(LocaleController.isRTL ? AndroidUtilities.dp(8) : 0,0,LocaleController.isRTL ? 0 : AndroidUtilities.dp(8),0);
frameLayout.addView(cell,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,48,Gravity.TOP | Gravity.LEFT,8,0,8,0));
cell.setOnClickListener(v -> {
CheckBoxCell cell1=(CheckBoxCell)v;
checks[0]=!checks[0];
cell1.setChecked(checks[0],true);
}
);
builder.setView(frameLayout);
}
 else {
builder.setMessage(LocaleController.getString("PinMessageAlertChannel",R.string.PinMessageAlertChannel));
checks=new boolean[]{false};
}
builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> MessagesController.getInstance(currentAccount).pinMessage(currentChat,currentUser,mid,checks[0]));
builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
showDialog(builder.create());
break;
}
case 14:
{
AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
builder.setTitle(LocaleController.getString("UnpinMessageAlertTitle",R.string.UnpinMessageAlertTitle));
builder.setMessage(LocaleController.getString("UnpinMessageAlert",R.string.UnpinMessageAlert));
builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> MessagesController.getInstance(currentAccount).pinMessage(currentChat,currentUser,0,false));
builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
showDialog(builder.create());
break;
}
case 15:
{
Bundle args=new Bundle();
args.putInt("user_id",selectedObject.messageOwner.media.user_id);
args.putString("phone",selectedObject.messageOwner.media.phone_number);
args.putBoolean("addContact",true);
presentFragment(new ContactAddActivity(args));
break;
}
case 16:
{
AndroidUtilities.addToClipboard(selectedObject.messageOwner.media.phone_number);
break;
}
case 17:
{
try {
Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + selectedObject.messageOwner.media.phone_number));
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
getParentActivity().startActivityForResult(intent,500);
}
 catch (Exception e) {
FileLog.e(e);
}
break;
}
case 18:
{
if (currentUser != null) {
VoIPHelper.startCall(currentUser,getParentActivity(),MessagesController.getInstance(currentAccount).getUserFull(currentUser.id));
}
break;
}
case 19:
{
VoIPHelper.showRateAlert(getParentActivity(),(TLRPC.TL_messageActionPhoneCall)selectedObject.messageOwner.action);
break;
}
case 20:
{
DataQuery.getInstance(currentAccount).addRecentSticker(DataQuery.TYPE_FAVE,selectedObject,selectedObject.getDocument(),(int)(System.currentTimeMillis() / 1000),false);
break;
}
case 21:
{
DataQuery.getInstance(currentAccount).addRecentSticker(DataQuery.TYPE_FAVE,selectedObject,selectedObject.getDocument(),(int)(System.currentTimeMillis() / 1000),true);
break;
}
case 22:
{
TLRPC.TL_channels_exportMessageLink req=new TLRPC.TL_channels_exportMessageLink();
req.id=selectedObject.getId();
req.channel=MessagesController.getInputChannel(currentChat);
ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
if (response != null) {
TLRPC.TL_exportedMessageLink exportedMessageLink=(TLRPC.TL_exportedMessageLink)response;
try {
android.content.ClipboardManager clipboard=(android.content.ClipboardManager)ApplicationLoader.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE);
ClipData clip=ClipData.newPlainText("label",exportedMessageLink.link);
clipboard.setPrimaryClip(clip);
if (exportedMessageLink.link.contains("/c/")) {
Toast.makeText(ApplicationLoader.applicationContext,LocaleController.getString("LinkCopiedPrivate",R.string.LinkCopiedPrivate),Toast.LENGTH_SHORT).show();
}
 else {
Toast.makeText(ApplicationLoader.applicationContext,LocaleController.getString("LinkCopied",R.string.LinkCopied),Toast.LENGTH_SHORT).show();
}
}
 catch (Exception e) {
FileLog.e(e);
}
}
}
));
break;
}
case 23:
{
AlertsCreator.createReportAlert(getParentActivity(),dialog_id,selectedObject.getId(),ChatActivity.this);
break;
}
case 24:
{
if (selectedObject.isEditing() || selectedObject.isSending() && selectedObjectGroup == null) {
SendMessagesHelper.getInstance(currentAccount).cancelSendingMessage(selectedObject);
}
 else if (selectedObject.isSending() && selectedObjectGroup != null) {
for (int a=0; a < selectedObjectGroup.messages.size(); a++) {
SendMessagesHelper.getInstance(currentAccount).cancelSendingMessage(new ArrayList<>(selectedObjectGroup.messages));
}
}
break;
}
case 25:
{
final AlertDialog[] progressDialog=new AlertDialog[]{new AlertDialog(getParentActivity(),3)};
int requestId=SendMessagesHelper.getInstance(currentAccount).sendVote(selectedObject,null,() -> {
try {
progressDialog[0].dismiss();
}
 catch (Throwable ignore) {
}
progressDialog[0]=null;
}
);
if (requestId != 0) {
AndroidUtilities.runOnUIThread(() -> {
if (progressDialog[0] == null) {
return;
}
progressDialog[0].setOnCancelListener(dialog -> ConnectionsManager.getInstance(currentAccount).cancelRequest(requestId,true));
showDialog(progressDialog[0]);
}
,500);
}
break;
}
case 26:
{
MessageObject object=selectedObject;
AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
builder.setTitle(LocaleController.getString("StopPollAlertTitle",R.string.StopPollAlertTitle));
builder.setMessage(LocaleController.getString("StopPollAlertText",R.string.StopPollAlertText));
builder.setPositiveButton(LocaleController.getString("Stop",R.string.Stop),(dialogInterface,i) -> {
final AlertDialog[] progressDialog=new AlertDialog[]{new AlertDialog(getParentActivity(),3)};
TLRPC.TL_messages_editMessage req=new TLRPC.TL_messages_editMessage();
TLRPC.TL_messageMediaPoll mediaPoll=(TLRPC.TL_messageMediaPoll)object.messageOwner.media;
TLRPC.TL_inputMediaPoll poll=new TLRPC.TL_inputMediaPoll();
poll.poll=new TLRPC.TL_poll();
poll.poll.id=mediaPoll.poll.id;
poll.poll.question=mediaPoll.poll.question;
poll.poll.answers=mediaPoll.poll.answers;
poll.poll.closed=true;
req.media=poll;
req.peer=MessagesController.getInstance(currentAccount).getInputPeer((int)dialog_id);
req.id=object.getId();
req.flags|=16384;
int requestId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
AndroidUtilities.runOnUIThread(() -> {
try {
progressDialog[0].dismiss();
}
 catch (Throwable ignore) {
}
progressDialog[0]=null;
}
);
if (error == null) {
MessagesController.getInstance(currentAccount).processUpdates((TLRPC.Updates)response,false);
}
 else {
AndroidUtilities.runOnUIThread(() -> AlertsCreator.processError(currentAccount,error,ChatActivity.this,req));
}
}
);
AndroidUtilities.runOnUIThread(() -> {
if (progressDialog[0] == null) {
return;
}
progressDialog[0].setOnCancelListener(dialog -> ConnectionsManager.getInstance(currentAccount).cancelRequest(requestId,true));
showDialog(progressDialog[0]);
}
,500);
}
);
builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
showDialog(builder.create());
break;
}
}
selectedObject=null;
selectedObjectGroup=null;
}
