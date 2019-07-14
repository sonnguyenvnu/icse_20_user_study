private void loadAudio(){
  loadingAudio=true;
  if (progressView != null) {
    progressView.showProgress();
  }
  Utilities.globalQueue.postRunnable(() -> {
    String[] projection={MediaStore.Audio.Media._ID,MediaStore.Audio.Media.ARTIST,MediaStore.Audio.Media.TITLE,MediaStore.Audio.Media.DATA,MediaStore.Audio.Media.DURATION,MediaStore.Audio.Media.ALBUM};
    final ArrayList<MediaController.AudioEntry> newAudioEntries=new ArrayList<>();
    try (Cursor cursor=ApplicationLoader.applicationContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,MediaStore.Audio.Media.IS_MUSIC + " != 0",null,MediaStore.Audio.Media.TITLE)){
      int id=-2000000000;
      while (cursor.moveToNext()) {
        MediaController.AudioEntry audioEntry=new MediaController.AudioEntry();
        audioEntry.id=cursor.getInt(0);
        audioEntry.author=cursor.getString(1);
        audioEntry.title=cursor.getString(2);
        audioEntry.path=cursor.getString(3);
        audioEntry.duration=(int)(cursor.getLong(4) / 1000);
        audioEntry.genre=cursor.getString(5);
        File file=new File(audioEntry.path);
        TLRPC.TL_message message=new TLRPC.TL_message();
        message.out=true;
        message.id=id;
        message.to_id=new TLRPC.TL_peerUser();
        message.to_id.user_id=message.from_id=UserConfig.getInstance(currentAccount).getClientUserId();
        message.date=(int)(System.currentTimeMillis() / 1000);
        message.message="";
        message.attachPath=audioEntry.path;
        message.media=new TLRPC.TL_messageMediaDocument();
        message.media.flags|=3;
        message.media.document=new TLRPC.TL_document();
        message.flags|=TLRPC.MESSAGE_FLAG_HAS_MEDIA | TLRPC.MESSAGE_FLAG_HAS_FROM_ID;
        String ext=FileLoader.getFileExtension(file);
        message.media.document.id=0;
        message.media.document.access_hash=0;
        message.media.document.file_reference=new byte[0];
        message.media.document.date=message.date;
        message.media.document.mime_type="audio/" + (ext.length() > 0 ? ext : "mp3");
        message.media.document.size=(int)file.length();
        message.media.document.dc_id=0;
        TLRPC.TL_documentAttributeAudio attributeAudio=new TLRPC.TL_documentAttributeAudio();
        attributeAudio.duration=audioEntry.duration;
        attributeAudio.title=audioEntry.title;
        attributeAudio.performer=audioEntry.author;
        attributeAudio.flags|=3;
        message.media.document.attributes.add(attributeAudio);
        TLRPC.TL_documentAttributeFilename fileName=new TLRPC.TL_documentAttributeFilename();
        fileName.file_name=file.getName();
        message.media.document.attributes.add(fileName);
        audioEntry.messageObject=new MessageObject(currentAccount,message,false);
        newAudioEntries.add(audioEntry);
        id--;
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    AndroidUtilities.runOnUIThread(() -> {
      audioEntries=newAudioEntries;
      progressView.showTextView();
      listViewAdapter.notifyDataSetChanged();
    }
);
  }
);
}
