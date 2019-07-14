private void processSelectedAttach(int which){
  if (which == attach_photo) {
    if (Build.VERSION.SDK_INT >= 23 && getParentActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
      getParentActivity().requestPermissions(new String[]{Manifest.permission.CAMERA},19);
      return;
    }
    try {
      Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      File image=AndroidUtilities.generatePicturePath();
      if (image != null) {
        if (Build.VERSION.SDK_INT >= 24) {
          takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,FileProvider.getUriForFile(getParentActivity(),BuildConfig.APPLICATION_ID + ".provider",image));
          takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
          takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
 else {
          takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(image));
        }
        currentPicturePath=image.getAbsolutePath();
      }
      startActivityForResult(takePictureIntent,0);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
 else   if (which == attach_gallery) {
    if (Build.VERSION.SDK_INT >= 23 && getParentActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      try {
        getParentActivity().requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},4);
      }
 catch (      Throwable ignore) {
      }
      return;
    }
    boolean allowGifs;
    if (ChatObject.isChannel(currentChat) && currentChat.banned_rights != null && currentChat.banned_rights.send_gifs) {
      allowGifs=false;
    }
 else {
      allowGifs=currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(currentEncryptedChat.layer) >= 46;
    }
    PhotoAlbumPickerActivity fragment=new PhotoAlbumPickerActivity(0,allowGifs,true,ChatActivity.this);
    fragment.setMaxSelectedPhotos(editingMessageObject != null ? 1 : 0);
    fragment.setDelegate(new PhotoAlbumPickerActivity.PhotoAlbumPickerActivityDelegate(){
      @Override public void didSelectPhotos(      ArrayList<SendMessagesHelper.SendingMediaInfo> photos){
        if (photos.isEmpty()) {
          return;
        }
        fillEditingMediaWithCaption(photos.get(0).caption,photos.get(0).entities);
        SendMessagesHelper.prepareSendingMedia(photos,dialog_id,replyingMessageObject,null,false,SharedConfig.groupPhotosEnabled,editingMessageObject);
        hideFieldPanel(false);
        DataQuery.getInstance(currentAccount).cleanDraft(dialog_id,true);
      }
      @Override public void startPhotoSelectActivity(){
        try {
          Intent videoPickerIntent=new Intent();
          videoPickerIntent.setType("video/*");
          videoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
          videoPickerIntent.putExtra(MediaStore.EXTRA_SIZE_LIMIT,(long)(1024 * 1024 * 1536));
          Intent photoPickerIntent=new Intent(Intent.ACTION_PICK);
          photoPickerIntent.setType("image/*");
          Intent chooserIntent=Intent.createChooser(photoPickerIntent,null);
          chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,new Intent[]{videoPickerIntent});
          startActivityForResult(chooserIntent,1);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
    }
);
    presentFragment(fragment);
  }
 else   if (which == attach_video) {
    if (Build.VERSION.SDK_INT >= 23 && getParentActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
      try {
        getParentActivity().requestPermissions(new String[]{Manifest.permission.CAMERA},20);
      }
 catch (      Throwable ignore) {
      }
      return;
    }
    try {
      Intent takeVideoIntent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
      File video=AndroidUtilities.generateVideoPath();
      if (video != null) {
        if (Build.VERSION.SDK_INT >= 24) {
          takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT,FileProvider.getUriForFile(getParentActivity(),BuildConfig.APPLICATION_ID + ".provider",video));
          takeVideoIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
          takeVideoIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
 else         if (Build.VERSION.SDK_INT >= 18) {
          takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(video));
        }
        takeVideoIntent.putExtra(MediaStore.EXTRA_SIZE_LIMIT,(long)(1024 * 1024 * 1536));
        currentPicturePath=video.getAbsolutePath();
      }
      startActivityForResult(takeVideoIntent,2);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
 else   if (which == attach_location) {
    if (!AndroidUtilities.isGoogleMapsInstalled(ChatActivity.this)) {
      return;
    }
    LocationActivity fragment=new LocationActivity(currentEncryptedChat == null ? 1 : 0);
    fragment.setDialogId(dialog_id);
    fragment.setDelegate(this);
    presentFragment(fragment);
  }
 else   if (which == attach_document) {
    if (Build.VERSION.SDK_INT >= 23 && getParentActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      try {
        getParentActivity().requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},4);
      }
 catch (      Throwable ignore) {
      }
      return;
    }
    DocumentSelectActivity fragment=new DocumentSelectActivity(true);
    fragment.setMaxSelectedFiles(editingMessageObject != null ? 1 : -1);
    fragment.setDelegate(new DocumentSelectActivity.DocumentSelectActivityDelegate(){
      @Override public void didSelectFiles(      DocumentSelectActivity activity,      ArrayList<String> files){
        activity.finishFragment();
        fillEditingMediaWithCaption(null,null);
        SendMessagesHelper.prepareSendingDocuments(files,files,null,null,null,dialog_id,replyingMessageObject,null,editingMessageObject);
        hideFieldPanel(false);
        DataQuery.getInstance(currentAccount).cleanDraft(dialog_id,true);
      }
      @Override public void startDocumentSelectActivity(){
        try {
          Intent photoPickerIntent=new Intent(Intent.ACTION_GET_CONTENT);
          if (Build.VERSION.SDK_INT >= 18) {
            photoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
          }
          photoPickerIntent.setType("*/*");
          startActivityForResult(photoPickerIntent,21);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      @Override public void startMusicSelectActivity(      BaseFragment parentFragment){
        AudioSelectActivity fragment=new AudioSelectActivity();
        fragment.setDelegate(audios -> {
          parentFragment.removeSelfFromStack();
          fillEditingMediaWithCaption(null,null);
          SendMessagesHelper.prepareSendingAudioDocuments(audios,dialog_id,replyingMessageObject,editingMessageObject);
          hideFieldPanel(false);
          DataQuery.getInstance(currentAccount).cleanDraft(dialog_id,true);
        }
);
        presentFragment(fragment);
      }
    }
);
    presentFragment(fragment);
  }
 else   if (which == attach_audio) {
    if (Build.VERSION.SDK_INT >= 23 && getParentActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      getParentActivity().requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},4);
      return;
    }
    AudioSelectActivity fragment=new AudioSelectActivity();
    fragment.setDelegate(audios -> {
      fillEditingMediaWithCaption(null,null);
      SendMessagesHelper.prepareSendingAudioDocuments(audios,dialog_id,replyingMessageObject,editingMessageObject);
      hideFieldPanel(false);
      DataQuery.getInstance(currentAccount).cleanDraft(dialog_id,true);
    }
);
    presentFragment(fragment);
  }
 else   if (which == attach_contact) {
    if (Build.VERSION.SDK_INT >= 23) {
      if (getParentActivity().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
        getParentActivity().requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},5);
        return;
      }
    }
    PhonebookSelectActivity activity=new PhonebookSelectActivity();
    activity.setDelegate(user -> {
      SendMessagesHelper.getInstance(currentAccount).sendMessage(user,dialog_id,replyingMessageObject,null,null);
      hideFieldPanel(false);
      DataQuery.getInstance(currentAccount).cleanDraft(dialog_id,true);
    }
);
    presentFragment(activity);
  }
 else   if (which == attach_poll) {
    if (currentChat == null || !ChatObject.canSendPolls(currentChat)) {
      return;
    }
    PollCreateActivity pollCreateActivity=new PollCreateActivity();
    pollCreateActivity.setDelegate(poll -> {
      SendMessagesHelper.getInstance(currentAccount).sendMessage(poll,dialog_id,replyingMessageObject,null,null);
      hideFieldPanel(false);
      DataQuery.getInstance(currentAccount).cleanDraft(dialog_id,true);
    }
);
    presentFragment(pollCreateActivity);
  }
}
