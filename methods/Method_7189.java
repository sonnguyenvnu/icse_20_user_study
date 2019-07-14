@UiThread public static void prepareSendingMedia(final ArrayList<SendingMediaInfo> media,final long dialog_id,final MessageObject reply_to_msg,final InputContentInfoCompat inputContent,final boolean forceDocument,final boolean groupPhotos,final MessageObject editingMessageObject){
  if (media.isEmpty()) {
    return;
  }
  final int currentAccount=UserConfig.selectedAccount;
  mediaSendQueue.postRunnable(() -> {
    long beginTime=System.currentTimeMillis();
    HashMap<SendingMediaInfo,MediaSendPrepareWorker> workers;
    int count=media.size();
    boolean isEncrypted=(int)dialog_id == 0;
    int enryptedLayer=0;
    if (isEncrypted) {
      int high_id=(int)(dialog_id >> 32);
      TLRPC.EncryptedChat encryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat(high_id);
      if (encryptedChat != null) {
        enryptedLayer=AndroidUtilities.getPeerLayerVersion(encryptedChat.layer);
      }
    }
    if ((!isEncrypted || enryptedLayer >= 73) && !forceDocument && groupPhotos) {
      workers=new HashMap<>();
      for (int a=0; a < count; a++) {
        final SendingMediaInfo info=media.get(a);
        if (info.searchImage == null && !info.isVideo) {
          String originalPath=info.path;
          String tempPath=info.path;
          if (tempPath == null && info.uri != null) {
            tempPath=AndroidUtilities.getPath(info.uri);
            originalPath=info.uri.toString();
          }
          if (tempPath != null && (tempPath.endsWith(".gif") || tempPath.endsWith(".webp"))) {
            continue;
          }
 else           if (ImageLoader.shouldSendImageAsDocument(info.path,info.uri)) {
            continue;
          }
 else           if (tempPath == null && info.uri != null) {
            if (MediaController.isGif(info.uri) || MediaController.isWebp(info.uri)) {
              continue;
            }
          }
          if (tempPath != null) {
            File temp=new File(tempPath);
            originalPath+=temp.length() + "_" + temp.lastModified();
          }
 else {
            originalPath=null;
          }
          TLRPC.TL_photo photo=null;
          String parentObject=null;
          if (!isEncrypted && info.ttl == 0) {
            Object[] sentData=MessagesStorage.getInstance(currentAccount).getSentFile(originalPath,!isEncrypted ? 0 : 3);
            if (sentData != null) {
              photo=(TLRPC.TL_photo)sentData[0];
              parentObject=(String)sentData[1];
            }
            if (photo == null && info.uri != null) {
              sentData=MessagesStorage.getInstance(currentAccount).getSentFile(AndroidUtilities.getPath(info.uri),!isEncrypted ? 0 : 3);
              if (sentData != null) {
                photo=(TLRPC.TL_photo)sentData[0];
                parentObject=(String)sentData[1];
              }
            }
            ensureMediaThumbExists(currentAccount,isEncrypted,photo,info.path,info.uri,0);
          }
          final MediaSendPrepareWorker worker=new MediaSendPrepareWorker();
          workers.put(info,worker);
          if (photo != null) {
            worker.parentObject=parentObject;
            worker.photo=photo;
          }
 else {
            worker.sync=new CountDownLatch(1);
            mediaSendThreadPool.execute(() -> {
              worker.photo=SendMessagesHelper.getInstance(currentAccount).generatePhotoSizes(info.path,info.uri);
              if (isEncrypted && info.canDeleteAfter) {
                new File(info.path).delete();
              }
              worker.sync.countDown();
            }
);
          }
        }
      }
    }
 else {
      workers=null;
    }
    long groupId=0;
    long lastGroupId=0;
    ArrayList<String> sendAsDocuments=null;
    ArrayList<String> sendAsDocumentsOriginal=null;
    ArrayList<String> sendAsDocumentsCaptions=null;
    ArrayList<ArrayList<TLRPC.MessageEntity>> sendAsDocumentsEntities=null;
    String extension=null;
    int photosCount=0;
    for (int a=0; a < count; a++) {
      final SendingMediaInfo info=media.get(a);
      if (groupPhotos && (!isEncrypted || enryptedLayer >= 73) && count > 1 && photosCount % 10 == 0) {
        lastGroupId=groupId=Utilities.random.nextLong();
        photosCount=0;
      }
      if (info.searchImage != null) {
        if (info.searchImage.type == 1) {
          final HashMap<String,String> params=new HashMap<>();
          TLRPC.TL_document document=null;
          String parentObject=null;
          File cacheFile;
          if (info.searchImage.document instanceof TLRPC.TL_document) {
            document=(TLRPC.TL_document)info.searchImage.document;
            cacheFile=FileLoader.getPathToAttach(document,true);
          }
 else {
            String md5=Utilities.MD5(info.searchImage.imageUrl) + "." + ImageLoader.getHttpUrlExtension(info.searchImage.imageUrl,"jpg");
            cacheFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),md5);
          }
          if (document == null) {
            if (info.searchImage.localUrl != null) {
              params.put("url",info.searchImage.localUrl);
            }
            File thumbFile=null;
            document=new TLRPC.TL_document();
            document.id=0;
            document.file_reference=new byte[0];
            document.date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
            TLRPC.TL_documentAttributeFilename fileName=new TLRPC.TL_documentAttributeFilename();
            fileName.file_name="animation.gif";
            document.attributes.add(fileName);
            document.size=info.searchImage.size;
            document.dc_id=0;
            if (cacheFile.toString().endsWith("mp4")) {
              document.mime_type="video/mp4";
              document.attributes.add(new TLRPC.TL_documentAttributeAnimated());
            }
 else {
              document.mime_type="image/gif";
            }
            if (cacheFile.exists()) {
              thumbFile=cacheFile;
            }
 else {
              cacheFile=null;
            }
            if (thumbFile == null) {
              String thumb=Utilities.MD5(info.searchImage.thumbUrl) + "." + ImageLoader.getHttpUrlExtension(info.searchImage.thumbUrl,"jpg");
              thumbFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),thumb);
              if (!thumbFile.exists()) {
                thumbFile=null;
              }
            }
            if (thumbFile != null) {
              try {
                int side=isEncrypted || info.ttl != 0 ? 90 : 320;
                Bitmap bitmap;
                if (thumbFile.getAbsolutePath().endsWith("mp4")) {
                  bitmap=ThumbnailUtils.createVideoThumbnail(thumbFile.getAbsolutePath(),MediaStore.Video.Thumbnails.MINI_KIND);
                }
 else {
                  bitmap=ImageLoader.loadBitmap(thumbFile.getAbsolutePath(),null,side,side,true);
                }
                if (bitmap != null) {
                  TLRPC.PhotoSize thumb=ImageLoader.scaleAndSaveImage(bitmap,side,side,side > 90 ? 80 : 55,isEncrypted);
                  if (thumb != null) {
                    document.thumbs.add(thumb);
                    document.flags|=1;
                  }
                  bitmap.recycle();
                }
              }
 catch (              Exception e) {
                FileLog.e(e);
              }
            }
            if (document.thumbs.isEmpty()) {
              TLRPC.TL_photoSize thumb=new TLRPC.TL_photoSize();
              thumb.w=info.searchImage.width;
              thumb.h=info.searchImage.height;
              thumb.size=0;
              thumb.location=new TLRPC.TL_fileLocationUnavailable();
              thumb.type="x";
              document.thumbs.add(thumb);
              document.flags|=1;
            }
          }
          final TLRPC.TL_document documentFinal=document;
          final String parentFinal=parentObject;
          final String originalPathFinal=info.searchImage.imageUrl;
          final String pathFinal=cacheFile == null ? info.searchImage.imageUrl : cacheFile.toString();
          if (params != null && info.searchImage.imageUrl != null) {
            params.put("originalPath",info.searchImage.imageUrl);
          }
          AndroidUtilities.runOnUIThread(() -> {
            if (editingMessageObject != null) {
              SendMessagesHelper.getInstance(currentAccount).editMessageMedia(editingMessageObject,null,null,documentFinal,pathFinal,params,false,parentFinal);
            }
 else {
              SendMessagesHelper.getInstance(currentAccount).sendMessage(documentFinal,null,pathFinal,dialog_id,reply_to_msg,info.caption,info.entities,null,params,0,parentFinal);
            }
          }
);
        }
 else {
          boolean needDownloadHttp=true;
          TLRPC.TL_photo photo=null;
          String parentObject=null;
          if (info.searchImage.photo instanceof TLRPC.TL_photo) {
            photo=(TLRPC.TL_photo)info.searchImage.photo;
          }
 else {
            if (!isEncrypted && info.ttl == 0) {
            }
          }
          if (photo == null) {
            String md5=Utilities.MD5(info.searchImage.imageUrl) + "." + ImageLoader.getHttpUrlExtension(info.searchImage.imageUrl,"jpg");
            File cacheFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),md5);
            if (cacheFile.exists() && cacheFile.length() != 0) {
              photo=SendMessagesHelper.getInstance(currentAccount).generatePhotoSizes(cacheFile.toString(),null);
              if (photo != null) {
                needDownloadHttp=false;
              }
            }
            if (photo == null) {
              md5=Utilities.MD5(info.searchImage.thumbUrl) + "." + ImageLoader.getHttpUrlExtension(info.searchImage.thumbUrl,"jpg");
              cacheFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),md5);
              if (cacheFile.exists()) {
                photo=SendMessagesHelper.getInstance(currentAccount).generatePhotoSizes(cacheFile.toString(),null);
              }
              if (photo == null) {
                photo=new TLRPC.TL_photo();
                photo.date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
                photo.file_reference=new byte[0];
                TLRPC.TL_photoSize photoSize=new TLRPC.TL_photoSize();
                photoSize.w=info.searchImage.width;
                photoSize.h=info.searchImage.height;
                photoSize.size=0;
                photoSize.location=new TLRPC.TL_fileLocationUnavailable();
                photoSize.type="x";
                photo.sizes.add(photoSize);
              }
            }
          }
          if (photo != null) {
            final TLRPC.TL_photo photoFinal=photo;
            final String parentFinal=parentObject;
            final boolean needDownloadHttpFinal=needDownloadHttp;
            final HashMap<String,String> params=new HashMap<>();
            if (info.searchImage.imageUrl != null) {
              params.put("originalPath",info.searchImage.imageUrl);
            }
            if (groupPhotos) {
              photosCount++;
              params.put("groupId","" + groupId);
              if (photosCount == 10 || a == count - 1) {
                params.put("final","1");
                lastGroupId=0;
              }
            }
            AndroidUtilities.runOnUIThread(() -> {
              if (editingMessageObject != null) {
                SendMessagesHelper.getInstance(currentAccount).editMessageMedia(editingMessageObject,photoFinal,null,null,needDownloadHttpFinal ? info.searchImage.imageUrl : null,params,false,parentFinal);
              }
 else {
                SendMessagesHelper.getInstance(currentAccount).sendMessage(photoFinal,needDownloadHttpFinal ? info.searchImage.imageUrl : null,dialog_id,reply_to_msg,info.caption,info.entities,null,params,info.ttl,parentFinal);
              }
            }
);
          }
        }
      }
 else {
        if (info.isVideo) {
          Bitmap thumb=null;
          String thumbKey=null;
          final VideoEditedInfo videoEditedInfo;
          if (forceDocument) {
            videoEditedInfo=null;
          }
 else {
            videoEditedInfo=info.videoEditedInfo != null ? info.videoEditedInfo : createCompressionSettings(info.path);
          }
          if (!forceDocument && (videoEditedInfo != null || info.path.endsWith("mp4"))) {
            String path=info.path;
            String originalPath=info.path;
            File temp=new File(originalPath);
            long startTime=0;
            boolean muted=false;
            originalPath+=temp.length() + "_" + temp.lastModified();
            if (videoEditedInfo != null) {
              muted=videoEditedInfo.muted;
              originalPath+=videoEditedInfo.estimatedDuration + "_" + videoEditedInfo.startTime + "_" + videoEditedInfo.endTime + (videoEditedInfo.muted ? "_m" : "");
              if (videoEditedInfo.resultWidth != videoEditedInfo.originalWidth) {
                originalPath+="_" + videoEditedInfo.resultWidth;
              }
              startTime=videoEditedInfo.startTime >= 0 ? videoEditedInfo.startTime : 0;
            }
            TLRPC.TL_document document=null;
            String parentObject=null;
            if (!isEncrypted && info.ttl == 0) {
              Object[] sentData=MessagesStorage.getInstance(currentAccount).getSentFile(originalPath,!isEncrypted ? 2 : 5);
              if (sentData != null) {
                document=(TLRPC.TL_document)sentData[0];
                parentObject=(String)sentData[1];
                ensureMediaThumbExists(currentAccount,isEncrypted,document,info.path,null,startTime);
              }
            }
            if (document == null) {
              thumb=createVideoThumbnail(info.path,startTime);
              if (thumb == null) {
                thumb=ThumbnailUtils.createVideoThumbnail(info.path,MediaStore.Video.Thumbnails.MINI_KIND);
              }
              TLRPC.PhotoSize size=null;
              if (thumb != null) {
                int side=isEncrypted || info.ttl != 0 ? 90 : Math.max(thumb.getWidth(),thumb.getHeight());
                size=ImageLoader.scaleAndSaveImage(thumb,side,side,side > 90 ? 80 : 55,isEncrypted);
                thumbKey=getKeyForPhotoSize(size,null,true);
              }
              document=new TLRPC.TL_document();
              document.file_reference=new byte[0];
              if (size != null) {
                document.thumbs.add(size);
                document.flags|=1;
              }
              document.mime_type="video/mp4";
              UserConfig.getInstance(currentAccount).saveConfig(false);
              TLRPC.TL_documentAttributeVideo attributeVideo;
              if (isEncrypted) {
                if (enryptedLayer >= 66) {
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
              document.attributes.add(attributeVideo);
              if (videoEditedInfo != null && videoEditedInfo.needConvert()) {
                if (videoEditedInfo.muted) {
                  fillVideoAttribute(info.path,attributeVideo,videoEditedInfo);
                  videoEditedInfo.originalWidth=attributeVideo.w;
                  videoEditedInfo.originalHeight=attributeVideo.h;
                }
 else {
                  attributeVideo.duration=(int)(videoEditedInfo.estimatedDuration / 1000);
                }
                if (videoEditedInfo.rotationValue == 90 || videoEditedInfo.rotationValue == 270) {
                  attributeVideo.w=videoEditedInfo.resultHeight;
                  attributeVideo.h=videoEditedInfo.resultWidth;
                }
 else {
                  attributeVideo.w=videoEditedInfo.resultWidth;
                  attributeVideo.h=videoEditedInfo.resultHeight;
                }
                document.size=(int)videoEditedInfo.estimatedSize;
              }
 else {
                if (temp.exists()) {
                  document.size=(int)temp.length();
                }
                fillVideoAttribute(info.path,attributeVideo,null);
              }
            }
            if (videoEditedInfo != null && videoEditedInfo.muted) {
              boolean found=false;
              for (int b=0, N=document.attributes.size(); b < N; b++) {
                if (document.attributes.get(b) instanceof TLRPC.TL_documentAttributeAnimated) {
                  found=true;
                  break;
                }
              }
              if (!found) {
                document.attributes.add(new TLRPC.TL_documentAttributeAnimated());
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
            if (originalPath != null) {
              params.put("originalPath",originalPath);
            }
            if (!muted && groupPhotos) {
              photosCount++;
              params.put("groupId","" + groupId);
              if (photosCount == 10 || a == count - 1) {
                params.put("final","1");
                lastGroupId=0;
              }
            }
            AndroidUtilities.runOnUIThread(() -> {
              if (thumbFinal != null && thumbKeyFinal != null) {
                ImageLoader.getInstance().putImageToCache(new BitmapDrawable(thumbFinal),thumbKeyFinal);
              }
              if (editingMessageObject != null) {
                SendMessagesHelper.getInstance(currentAccount).editMessageMedia(editingMessageObject,null,videoEditedInfo,videoFinal,finalPath,params,false,parentFinal);
              }
 else {
                SendMessagesHelper.getInstance(currentAccount).sendMessage(videoFinal,videoEditedInfo,finalPath,dialog_id,reply_to_msg,info.caption,info.entities,null,params,info.ttl,parentFinal);
              }
            }
);
          }
 else {
            prepareSendingDocumentInternal(currentAccount,info.path,info.path,null,null,dialog_id,reply_to_msg,info.caption,info.entities,editingMessageObject,forceDocument);
          }
        }
 else {
          String originalPath=info.path;
          String tempPath=info.path;
          if (tempPath == null && info.uri != null) {
            tempPath=AndroidUtilities.getPath(info.uri);
            originalPath=info.uri.toString();
          }
          boolean isDocument=false;
          if (forceDocument || ImageLoader.shouldSendImageAsDocument(info.path,info.uri)) {
            isDocument=true;
            extension=tempPath != null ? FileLoader.getFileExtension(new File(tempPath)) : "";
          }
 else           if (tempPath != null && (tempPath.endsWith(".gif") || tempPath.endsWith(".webp"))) {
            if (tempPath.endsWith(".gif")) {
              extension="gif";
            }
 else {
              extension="webp";
            }
            isDocument=true;
          }
 else           if (tempPath == null && info.uri != null) {
            if (MediaController.isGif(info.uri)) {
              isDocument=true;
              originalPath=info.uri.toString();
              tempPath=MediaController.copyFileToCache(info.uri,"gif");
              extension="gif";
            }
 else             if (MediaController.isWebp(info.uri)) {
              isDocument=true;
              originalPath=info.uri.toString();
              tempPath=MediaController.copyFileToCache(info.uri,"webp");
              extension="webp";
            }
          }
          if (isDocument) {
            if (sendAsDocuments == null) {
              sendAsDocuments=new ArrayList<>();
              sendAsDocumentsOriginal=new ArrayList<>();
              sendAsDocumentsCaptions=new ArrayList<>();
              sendAsDocumentsEntities=new ArrayList<>();
            }
            sendAsDocuments.add(tempPath);
            sendAsDocumentsOriginal.add(originalPath);
            sendAsDocumentsCaptions.add(info.caption);
            sendAsDocumentsEntities.add(info.entities);
          }
 else {
            if (tempPath != null) {
              File temp=new File(tempPath);
              originalPath+=temp.length() + "_" + temp.lastModified();
            }
 else {
              originalPath=null;
            }
            TLRPC.TL_photo photo=null;
            String parentObject=null;
            if (workers != null) {
              MediaSendPrepareWorker worker=workers.get(info);
              photo=worker.photo;
              parentObject=worker.parentObject;
              if (photo == null) {
                try {
                  worker.sync.await();
                }
 catch (                Exception e) {
                  FileLog.e(e);
                }
                photo=worker.photo;
                parentObject=worker.parentObject;
              }
            }
 else {
              if (!isEncrypted && info.ttl == 0) {
                Object[] sentData=MessagesStorage.getInstance(currentAccount).getSentFile(originalPath,!isEncrypted ? 0 : 3);
                if (sentData != null) {
                  photo=(TLRPC.TL_photo)sentData[0];
                  parentObject=(String)sentData[1];
                }
                if (photo == null && info.uri != null) {
                  sentData=MessagesStorage.getInstance(currentAccount).getSentFile(AndroidUtilities.getPath(info.uri),!isEncrypted ? 0 : 3);
                  if (sentData != null) {
                    photo=(TLRPC.TL_photo)sentData[0];
                    parentObject=(String)sentData[1];
                  }
                }
                ensureMediaThumbExists(currentAccount,isEncrypted,photo,info.path,info.uri,0);
              }
              if (photo == null) {
                photo=SendMessagesHelper.getInstance(currentAccount).generatePhotoSizes(info.path,info.uri);
                if (isEncrypted && info.canDeleteAfter) {
                  new File(info.path).delete();
                }
              }
            }
            if (photo != null) {
              final TLRPC.TL_photo photoFinal=photo;
              final String parentFinal=parentObject;
              final HashMap<String,String> params=new HashMap<>();
              final Bitmap[] bitmapFinal=new Bitmap[1];
              final String[] keyFinal=new String[1];
              if (photo.has_stickers=info.masks != null && !info.masks.isEmpty()) {
                SerializedData serializedData=new SerializedData(4 + info.masks.size() * 20);
                serializedData.writeInt32(info.masks.size());
                for (int b=0; b < info.masks.size(); b++) {
                  info.masks.get(b).serializeToStream(serializedData);
                }
                params.put("masks",Utilities.bytesToHex(serializedData.toByteArray()));
                serializedData.cleanup();
              }
              if (originalPath != null) {
                params.put("originalPath",originalPath);
              }
              try {
                if (!groupPhotos || media.size() == 1) {
                  TLRPC.PhotoSize currentPhotoObject=FileLoader.getClosestPhotoSizeWithSize(photoFinal.sizes,AndroidUtilities.getPhotoSize());
                  if (currentPhotoObject != null) {
                    keyFinal[0]=getKeyForPhotoSize(currentPhotoObject,bitmapFinal,false);
                  }
                }
              }
 catch (              Exception e) {
                FileLog.e(e);
              }
              if (groupPhotos) {
                photosCount++;
                params.put("groupId","" + groupId);
                if (photosCount == 10 || a == count - 1) {
                  params.put("final","1");
                  lastGroupId=0;
                }
              }
              AndroidUtilities.runOnUIThread(() -> {
                if (bitmapFinal[0] != null && keyFinal[0] != null) {
                  ImageLoader.getInstance().putImageToCache(new BitmapDrawable(bitmapFinal[0]),keyFinal[0]);
                }
                if (editingMessageObject != null) {
                  SendMessagesHelper.getInstance(currentAccount).editMessageMedia(editingMessageObject,photoFinal,null,null,null,params,false,parentFinal);
                }
 else {
                  SendMessagesHelper.getInstance(currentAccount).sendMessage(photoFinal,null,dialog_id,reply_to_msg,info.caption,info.entities,null,params,info.ttl,parentFinal);
                }
              }
);
            }
 else {
              if (sendAsDocuments == null) {
                sendAsDocuments=new ArrayList<>();
                sendAsDocumentsOriginal=new ArrayList<>();
                sendAsDocumentsCaptions=new ArrayList<>();
                sendAsDocumentsEntities=new ArrayList<>();
              }
              sendAsDocuments.add(tempPath);
              sendAsDocumentsOriginal.add(originalPath);
              sendAsDocumentsCaptions.add(info.caption);
              sendAsDocumentsEntities.add(info.entities);
            }
          }
        }
      }
    }
    if (lastGroupId != 0) {
      final long lastGroupIdFinal=lastGroupId;
      AndroidUtilities.runOnUIThread(() -> {
        SendMessagesHelper instance=getInstance(currentAccount);
        ArrayList<DelayedMessage> arrayList=instance.delayedMessages.get("group_" + lastGroupIdFinal);
        if (arrayList != null && !arrayList.isEmpty()) {
          DelayedMessage message=arrayList.get(0);
          MessageObject prevMessage=message.messageObjects.get(message.messageObjects.size() - 1);
          message.finalGroupMessage=prevMessage.getId();
          prevMessage.messageOwner.params.put("final","1");
          TLRPC.TL_messages_messages messagesRes=new TLRPC.TL_messages_messages();
          messagesRes.messages.add(prevMessage.messageOwner);
          MessagesStorage.getInstance(currentAccount).putMessages(messagesRes,message.peer,-2,0,false);
          instance.sendReadyToSendGroup(message,true,true);
        }
      }
);
    }
    if (inputContent != null) {
      inputContent.releasePermission();
    }
    if (sendAsDocuments != null && !sendAsDocuments.isEmpty()) {
      for (int a=0; a < sendAsDocuments.size(); a++) {
        prepareSendingDocumentInternal(currentAccount,sendAsDocuments.get(a),sendAsDocumentsOriginal.get(a),null,extension,dialog_id,reply_to_msg,sendAsDocumentsCaptions.get(a),sendAsDocumentsEntities.get(a),editingMessageObject,forceDocument);
      }
    }
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("total send time = " + (System.currentTimeMillis() - beginTime));
    }
  }
);
}
