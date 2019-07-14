private void processSelectedOption(int option){
  if (selectedObject == null) {
    return;
  }
switch (option) {
case 3:
{
      AndroidUtilities.addToClipboard(getMessageContent(selectedObject,0,true));
      break;
    }
case 4:
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
    if (selectedObject.type == 3 || selectedObject.type == 1) {
      if (Build.VERSION.SDK_INT >= 23 && getParentActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        getParentActivity().requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},4);
        selectedObject=null;
        return;
      }
      MediaController.saveFile(path,getParentActivity(),selectedObject.type == 3 ? 1 : 0,null,null);
    }
    break;
  }
case 5:
{
  File locFile=null;
  if (selectedObject.messageOwner.attachPath != null && selectedObject.messageOwner.attachPath.length() != 0) {
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
        int lastPosition=chatLayoutManager.findLastVisibleItemPosition();
        if (lastPosition < chatLayoutManager.getItemCount() - 1) {
          scrollToPositionOnRecreate=chatLayoutManager.findFirstVisibleItemPosition();
          RecyclerListView.Holder holder=(RecyclerListView.Holder)chatListView.findViewHolderForAdapterPosition(scrollToPositionOnRecreate);
          if (holder != null) {
            scrollToOffsetOnRecreate=holder.itemView.getTop();
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
if (Build.VERSION.SDK_INT >= 24) {
  try {
    intent.putExtra(Intent.EXTRA_STREAM,FileProvider.getUriForFile(getParentActivity(),BuildConfig.APPLICATION_ID + ".provider",new File(path)));
    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
  }
 catch (  Exception ignore) {
    intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(new File(path)));
  }
}
 else {
  intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(new File(path)));
}
getParentActivity().startActivityForResult(Intent.createChooser(intent,LocaleController.getString("ShareFile",R.string.ShareFile)),500);
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
return;
}
MediaController.saveFile(path,getParentActivity(),0,null,null);
break;
}
case 9:
{
showDialog(new StickersAlert(getParentActivity(),this,selectedObject.getInputStickerSet(),null,null));
break;
}
case 10:
{
if (Build.VERSION.SDK_INT >= 23 && getParentActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
getParentActivity().requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},4);
selectedObject=null;
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
}
selectedObject=null;
}
