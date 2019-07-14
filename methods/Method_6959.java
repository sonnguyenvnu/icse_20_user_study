private void loadPendingTasks(){
  storageQueue.postRunnable(() -> {
    try {
      SQLiteCursor cursor=database.queryFinalized("SELECT id, data FROM pending_tasks WHERE 1");
      while (cursor.next()) {
        final long taskId=cursor.longValue(0);
        NativeByteBuffer data=cursor.byteBufferValue(1);
        if (data != null) {
          int type=data.readInt32(false);
switch (type) {
case 0:
{
              final TLRPC.Chat chat=TLRPC.Chat.TLdeserialize(data,data.readInt32(false),false);
              if (chat != null) {
                Utilities.stageQueue.postRunnable(() -> MessagesController.getInstance(currentAccount).loadUnknownChannel(chat,taskId));
              }
              break;
            }
case 1:
{
            final int channelId=data.readInt32(false);
            final int newDialogType=data.readInt32(false);
            Utilities.stageQueue.postRunnable(() -> MessagesController.getInstance(currentAccount).getChannelDifference(channelId,newDialogType,taskId,null));
            break;
          }
case 2:
case 5:
case 8:
case 10:
case 14:
{
          final TLRPC.Dialog dialog=new TLRPC.TL_dialog();
          dialog.id=data.readInt64(false);
          dialog.top_message=data.readInt32(false);
          dialog.read_inbox_max_id=data.readInt32(false);
          dialog.read_outbox_max_id=data.readInt32(false);
          dialog.unread_count=data.readInt32(false);
          dialog.last_message_date=data.readInt32(false);
          dialog.pts=data.readInt32(false);
          dialog.flags=data.readInt32(false);
          if (type >= 5) {
            dialog.pinned=data.readBool(false);
            dialog.pinnedNum=data.readInt32(false);
          }
          if (type >= 8) {
            dialog.unread_mentions_count=data.readInt32(false);
          }
          if (type >= 10) {
            dialog.unread_mark=data.readBool(false);
          }
          if (type >= 14) {
            dialog.folder_id=data.readInt32(false);
          }
          final TLRPC.InputPeer peer=TLRPC.InputPeer.TLdeserialize(data,data.readInt32(false),false);
          AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).checkLastDialogMessage(dialog,peer,taskId));
          break;
        }
case 3:
{
        long random_id=data.readInt64(false);
        TLRPC.InputPeer peer=TLRPC.InputPeer.TLdeserialize(data,data.readInt32(false),false);
        TLRPC.TL_inputMediaGame game=(TLRPC.TL_inputMediaGame)TLRPC.InputMedia.TLdeserialize(data,data.readInt32(false),false);
        SendMessagesHelper.getInstance(currentAccount).sendGame(peer,game,random_id,taskId);
        break;
      }
case 4:
{
      final long did=data.readInt64(false);
      final boolean pin=data.readBool(false);
      final TLRPC.InputPeer peer=TLRPC.InputPeer.TLdeserialize(data,data.readInt32(false),false);
      AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).pinDialog(did,pin,peer,taskId));
      break;
    }
case 6:
{
    final int channelId=data.readInt32(false);
    final int newDialogType=data.readInt32(false);
    final TLRPC.InputChannel inputChannel=TLRPC.InputChannel.TLdeserialize(data,data.readInt32(false),false);
    Utilities.stageQueue.postRunnable(() -> MessagesController.getInstance(currentAccount).getChannelDifference(channelId,newDialogType,taskId,inputChannel));
    break;
  }
case 7:
{
  final int channelId=data.readInt32(false);
  int constructor=data.readInt32(false);
  TLObject request=TLRPC.TL_messages_deleteMessages.TLdeserialize(data,constructor,false);
  if (request == null) {
    request=TLRPC.TL_channels_deleteMessages.TLdeserialize(data,constructor,false);
  }
  if (request == null) {
    removePendingTask(taskId);
  }
 else {
    final TLObject finalRequest=request;
    AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).deleteMessages(null,null,null,channelId,true,taskId,finalRequest));
  }
  break;
}
case 9:
{
final long did=data.readInt64(false);
final TLRPC.InputPeer peer=TLRPC.InputPeer.TLdeserialize(data,data.readInt32(false),false);
AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).markDialogAsUnread(did,peer,taskId));
break;
}
case 11:
{
TLRPC.InputChannel inputChannel;
final int mid=data.readInt32(false);
final int channelId=data.readInt32(false);
final int ttl=data.readInt32(false);
if (channelId != 0) {
inputChannel=TLRPC.InputChannel.TLdeserialize(data,data.readInt32(false),false);
}
 else {
inputChannel=null;
}
AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).markMessageAsRead(mid,channelId,inputChannel,ttl,taskId));
break;
}
case 12:
{
long wallPaperId=data.readInt64(false);
long accessHash=data.readInt64(false);
boolean isBlurred=data.readBool(false);
boolean isMotion=data.readBool(false);
int backgroundColor=data.readInt32(false);
float intesity=(float)data.readDouble(false);
boolean install=data.readBool(false);
AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).saveWallpaperToServer(null,wallPaperId,accessHash,isBlurred,isMotion,backgroundColor,intesity,install,taskId));
break;
}
case 13:
{
final long did=data.readInt64(false);
final boolean first=data.readBool(false);
final int onlyHistory=data.readInt32(false);
final int maxIdDelete=data.readInt32(false);
final boolean revoke=data.readBool(false);
TLRPC.InputPeer inputPeer=TLRPC.InputPeer.TLdeserialize(data,data.readInt32(false),false);
AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).deleteDialog(did,first,onlyHistory,maxIdDelete,revoke,inputPeer,taskId));
break;
}
case 15:
{
TLRPC.InputPeer inputPeer=TLRPC.InputPeer.TLdeserialize(data,data.readInt32(false),false);
Utilities.stageQueue.postRunnable(() -> MessagesController.getInstance(currentAccount).loadUnknownDialog(inputPeer,taskId));
break;
}
case 16:
{
final int folderId=data.readInt32(false);
int count=data.readInt32(false);
ArrayList<TLRPC.InputDialogPeer> peers=new ArrayList<>();
for (int a=0; a < count; a++) {
TLRPC.InputDialogPeer inputPeer=TLRPC.InputDialogPeer.TLdeserialize(data,data.readInt32(false),false);
peers.add(inputPeer);
}
AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).reorderPinnedDialogs(folderId,peers,taskId));
break;
}
case 17:
{
final int folderId=data.readInt32(false);
int count=data.readInt32(false);
ArrayList<TLRPC.TL_inputFolderPeer> peers=new ArrayList<>();
for (int a=0; a < count; a++) {
TLRPC.TL_inputFolderPeer inputPeer=TLRPC.TL_inputFolderPeer.TLdeserialize(data,data.readInt32(false),false);
peers.add(inputPeer);
}
AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).addDialogToFolder(null,folderId,-1,peers,taskId));
break;
}
}
data.reuse();
}
}
cursor.dispose();
}
 catch (Exception e) {
FileLog.e(e);
}
}
);
}
