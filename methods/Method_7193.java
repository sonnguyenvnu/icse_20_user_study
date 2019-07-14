@UiThread public static void prepareSendingVideo(final String videoPath,final long estimatedSize,final long duration,final int width,final int height,final VideoEditedInfo info,final long dialog_id,final MessageObject reply_to_msg,final CharSequence caption,final ArrayList<TLRPC.MessageEntity> entities,final int ttl,final MessageObject editingMessageObject){
  if (videoPath == null || videoPath.length() == 0) {
    return;
  }
  final int currentAccount=UserConfig.selectedAccount;
  new Thread(() -> {
    final VideoEditedInfo videoEditedInfo=info != null ? info : createCompressionSettings(videoPath);
    boolean isEncrypted=(int)dialog_id == 0;
    boolean isRound=videoEditedInfo != null && videoEditedInfo.roundVideo;
    Bitmap thumb=null;
    String thumbKey=null;
    if (videoEditedInfo != null || videoPath.endsWith("mp4") || isRound) {
      String path=videoPath;
      String originalPath=videoPath;
      File temp=new File(originalPath);
      long startTime=0;
      originalPath+=temp.length() + "_" + temp.lastModified();
      if (videoEditedInfo != null) {
        if (!isRound) {
          originalPath+=duration + "_" + videoEditedInfo.startTime + "_" + videoEditedInfo.endTime + (videoEditedInfo.muted ? "_m" : "");
          if (videoEditedInfo.resultWidth != videoEditedInfo.originalWidth) {
            originalPath+="_" + videoEditedInfo.resultWidth;
          }
        }
        startTime=videoEditedInfo.startTime >= 0 ? videoEditedInfo.startTime : 0;
      }
      TLRPC.TL_document document=null;
      String parentObject=null;
      if (!isEncrypted && ttl == 0) {
        Object[] sentData=MessagesStorage.getInstance(currentAccount).getSentFile(originalPath,!isEncrypted ? 2 : 5);
        if (sentData != null) {
          document=(TLRPC.TL_document)sentData[0];
          parentObject=(String)sentData[1];
          ensureMediaThumbExists(currentAccount,isEncrypted,document,videoPath,null,startTime);
        }
      }
      if (document == null) {
        thumb=createVideoThumbnail(videoPath,startTime);
        if (thumb == null) {
          thumb=ThumbnailUtils.createVideoThumbnail(videoPath,MediaStore.Video.Thumbnails.MINI_KIND);
        }
        int side=isEncrypted || ttl != 0 ? 90 : 320;
        TLRPC.PhotoSize size=ImageLoader.scaleAndSaveImage(thumb,side,side,side > 90 ? 80 : 55,isEncrypted);
        if (thumb != null && size != null) {
          if (isRound) {
            if (isEncrypted) {
              Utilities.blurBitmap(thumb,7,Build.VERSION.SDK_INT < 21 ? 0 : 1,thumb.getWidth(),thumb.getHeight(),thumb.getRowBytes());
              thumbKey=String.format(size.location.volume_id + "_" + size.location.local_id + "@%d_%d_b2",(int)(AndroidUtilities.roundMessageSize / AndroidUtilities.density),(int)(AndroidUtilities.roundMessageSize / AndroidUtilities.density));
            }
 else {
              Utilities.blurBitmap(thumb,3,Build.VERSION.SDK_INT < 21 ? 0 : 1,thumb.getWidth(),thumb.getHeight(),thumb.getRowBytes());
              thumbKey=String.format(size.location.volume_id + "_" + size.location.local_id + "@%d_%d_b",(int)(AndroidUtilities.roundMessageSize / AndroidUtilities.density),(int)(AndroidUtilities.roundMessageSize / AndroidUtilities.density));
            }
          }
 else {
            thumb=null;
          }
        }
        document=new TLRPC.TL_document();
        if (size != null) {
          document.thumbs.add(size);
          document.flags|=1;
        }
        document.file_reference=new byte[0];
        document.mime_type="video/mp4";
        UserConfig.getInstance(currentAccount).saveConfig(false);
        TLRPC.TL_documentAttributeVideo attributeVideo;
        if (isEncrypted) {
          int high_id=(int)(dialog_id >> 32);
          TLRPC.EncryptedChat encryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat(high_id);
          if (encryptedChat == null) {
            return;
          }
          if (AndroidUtilities.getPeerLayerVersion(encryptedChat.layer) >= 66) {
            attributeVideo=new TLRPC.TL_documentAttributeVideo();
          }
 else {
            attributeVideo=new TLRPC.TL_documentAttributeVideo_layer65();
          }
        }
 else {
          attributeVideo=new TLRPC.TL_documentAttributeVideo();
          attributeVideo.supports_streaming=true;
        }
        attributeVideo.round_message=isRound;
        document.attributes.add(attributeVideo);
        if (videoEditedInfo != null && videoEditedInfo.needConvert()) {
          if (videoEditedInfo.muted) {
            document.attributes.add(new TLRPC.TL_documentAttributeAnimated());
            fillVideoAttribute(videoPath,attributeVideo,videoEditedInfo);
            videoEditedInfo.originalWidth=attributeVideo.w;
            videoEditedInfo.originalHeight=attributeVideo.h;
          }
 else {
            attributeVideo.duration=(int)(duration / 1000);
          }
          if (videoEditedInfo.rotationValue == 90 || videoEditedInfo.rotationValue == 270) {
            attributeVideo.w=height;
            attributeVideo.h=width;
          }
 else {
            attributeVideo.w=width;
            attributeVideo.h=height;
          }
          document.size=(int)estimatedSize;
        }
 else {
          if (temp.exists()) {
            document.size=(int)temp.length();
          }
          fillVideoAttribute(videoPath,attributeVideo,null);
        }
      }
      if (videoEditedInfo != null && videoEditedInfo.needConvert()) {
        String fileName=Integer.MIN_VALUE + "_" + SharedConfig.getLastLocalId() + ".mp4";
        File cacheFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),fileName);
        SharedConfig.saveConfig();
        path=cacheFile.getAbsolutePath();
      }
      final TLRPC.TL_document videoFinal=document;
      final String parentFinal=parentObject;
      final String originalPathFinal=originalPath;
      final String finalPath=path;
      final HashMap<String,String> params=new HashMap<>();
      final Bitmap thumbFinal=thumb;
      final String thumbKeyFinal=thumbKey;
      final String captionFinal=caption != null ? caption.toString() : "";
      if (originalPath != null) {
        params.put("originalPath",originalPath);
      }
      AndroidUtilities.runOnUIThread(() -> {
        if (thumbFinal != null && thumbKeyFinal != null) {
          ImageLoader.getInstance().putImageToCache(new BitmapDrawable(thumbFinal),thumbKeyFinal);
        }
        if (editingMessageObject != null) {
          SendMessagesHelper.getInstance(currentAccount).editMessageMedia(editingMessageObject,null,videoEditedInfo,videoFinal,finalPath,params,false,parentFinal);
        }
 else {
          SendMessagesHelper.getInstance(currentAccount).sendMessage(videoFinal,videoEditedInfo,finalPath,dialog_id,reply_to_msg,captionFinal,entities,null,params,ttl,parentFinal);
        }
      }
);
    }
 else {
      prepareSendingDocumentInternal(currentAccount,videoPath,videoPath,null,null,dialog_id,reply_to_msg,caption,entities,editingMessageObject,false);
    }
  }
).start();
}
