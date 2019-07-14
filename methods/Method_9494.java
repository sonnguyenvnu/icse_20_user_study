private void setIndexToImage(ImageReceiver imageReceiver,int index){
  imageReceiver.setOrientation(0,false);
  if (!secureDocuments.isEmpty()) {
    if (index >= 0 && index < secureDocuments.size()) {
      Object object=secureDocuments.get(index);
      int size=(int)(AndroidUtilities.getPhotoSize() / AndroidUtilities.density);
      ImageReceiver.BitmapHolder placeHolder=null;
      if (currentThumb != null && imageReceiver == centerImage) {
        placeHolder=currentThumb;
      }
      if (placeHolder == null) {
        placeHolder=placeProvider.getThumbForPhoto(null,null,index);
      }
      SecureDocument document=secureDocuments.get(index);
      int imageSize=document.secureFile.size;
      imageReceiver.setImage(ImageLocation.getForSecureDocument(document),"d",null,null,placeHolder != null ? new BitmapDrawable(placeHolder.bitmap) : null,imageSize,null,null,0);
    }
  }
 else   if (!imagesArrLocals.isEmpty()) {
    if (index >= 0 && index < imagesArrLocals.size()) {
      Object object=imagesArrLocals.get(index);
      int size=(int)(AndroidUtilities.getPhotoSize() / AndroidUtilities.density);
      ImageReceiver.BitmapHolder placeHolder=null;
      if (currentThumb != null && imageReceiver == centerImage) {
        placeHolder=currentThumb;
      }
      if (placeHolder == null) {
        placeHolder=placeProvider.getThumbForPhoto(null,null,index);
      }
      String path=null;
      TLRPC.Document document=null;
      WebFile webDocument=null;
      TLRPC.PhotoSize photo=null;
      TLObject photoObject=null;
      int imageSize=0;
      String filter=null;
      boolean isVideo=false;
      int cacheType=0;
      if (object instanceof MediaController.PhotoEntry) {
        MediaController.PhotoEntry photoEntry=(MediaController.PhotoEntry)object;
        isVideo=photoEntry.isVideo;
        if (!photoEntry.isVideo) {
          if (photoEntry.imagePath != null) {
            path=photoEntry.imagePath;
          }
 else {
            imageReceiver.setOrientation(photoEntry.orientation,false);
            path=photoEntry.path;
          }
          filter=String.format(Locale.US,"%d_%d",size,size);
        }
 else {
          if (photoEntry.thumbPath != null) {
            path=photoEntry.thumbPath;
          }
 else {
            path="vthumb://" + photoEntry.imageId + ":" + photoEntry.path;
          }
        }
      }
 else       if (object instanceof TLRPC.BotInlineResult) {
        cacheType=1;
        TLRPC.BotInlineResult botInlineResult=((TLRPC.BotInlineResult)object);
        if (botInlineResult.type.equals("video") || MessageObject.isVideoDocument(botInlineResult.document)) {
          if (botInlineResult.document != null) {
            photo=FileLoader.getClosestPhotoSizeWithSize(botInlineResult.document.thumbs,90);
            photoObject=botInlineResult.document;
          }
 else           if (botInlineResult.thumb instanceof TLRPC.TL_webDocument) {
            webDocument=WebFile.createWithWebDocument(botInlineResult.thumb);
          }
        }
 else         if (botInlineResult.type.equals("gif") && botInlineResult.document != null) {
          document=botInlineResult.document;
          imageSize=botInlineResult.document.size;
          filter="d";
        }
 else         if (botInlineResult.photo != null) {
          TLRPC.PhotoSize sizeFull=FileLoader.getClosestPhotoSizeWithSize(botInlineResult.photo.sizes,AndroidUtilities.getPhotoSize());
          photo=sizeFull;
          photoObject=botInlineResult.photo;
          imageSize=sizeFull.size;
          filter=String.format(Locale.US,"%d_%d",size,size);
        }
 else         if (botInlineResult.content instanceof TLRPC.TL_webDocument) {
          if (botInlineResult.type.equals("gif")) {
            filter="d";
          }
 else {
            filter=String.format(Locale.US,"%d_%d",size,size);
          }
          webDocument=WebFile.createWithWebDocument(botInlineResult.content);
        }
      }
 else       if (object instanceof MediaController.SearchImage) {
        cacheType=1;
        MediaController.SearchImage photoEntry=(MediaController.SearchImage)object;
        if (photoEntry.photoSize != null) {
          photo=photoEntry.photoSize;
          photoObject=photoEntry.photo;
          imageSize=photoEntry.photoSize.size;
        }
 else         if (photoEntry.imagePath != null) {
          path=photoEntry.imagePath;
        }
 else         if (photoEntry.document != null) {
          document=photoEntry.document;
          imageSize=photoEntry.document.size;
        }
 else {
          path=photoEntry.imageUrl;
          imageSize=photoEntry.size;
        }
        filter="d";
      }
      if (document != null) {
        TLRPC.PhotoSize thumb=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,90);
        imageReceiver.setImage(ImageLocation.getForDocument(document),"d",placeHolder == null ? ImageLocation.getForDocument(thumb,document) : null,String.format(Locale.US,"%d_%d",size,size),placeHolder != null ? new BitmapDrawable(placeHolder.bitmap) : null,imageSize,null,object,cacheType);
      }
 else       if (photo != null) {
        imageReceiver.setImage(ImageLocation.getForObject(photo,photoObject),filter,placeHolder != null ? new BitmapDrawable(placeHolder.bitmap) : null,imageSize,null,object,cacheType);
      }
 else       if (webDocument != null) {
        imageReceiver.setImage(ImageLocation.getForWebFile(webDocument),filter,placeHolder != null ? new BitmapDrawable(placeHolder.bitmap) : (isVideo && parentActivity != null ? parentActivity.getResources().getDrawable(R.drawable.nophotos) : null),null,object,cacheType);
      }
 else {
        imageReceiver.setImage(path,filter,placeHolder != null ? new BitmapDrawable(placeHolder.bitmap) : (isVideo && parentActivity != null ? parentActivity.getResources().getDrawable(R.drawable.nophotos) : null),null,imageSize);
      }
    }
 else {
      imageReceiver.setImageBitmap((Bitmap)null);
    }
  }
 else {
    MessageObject messageObject;
    if (!imagesArr.isEmpty() && index >= 0 && index < imagesArr.size()) {
      messageObject=imagesArr.get(index);
      imageReceiver.setShouldGenerateQualityThumb(true);
    }
 else {
      messageObject=null;
    }
    if (messageObject != null) {
      if (messageObject.isVideo()) {
        imageReceiver.setNeedsQualityThumb(true);
        if (messageObject.photoThumbs != null && !messageObject.photoThumbs.isEmpty()) {
          ImageReceiver.BitmapHolder placeHolder=null;
          if (currentThumb != null && imageReceiver == centerImage) {
            placeHolder=currentThumb;
          }
          TLRPC.PhotoSize thumbLocation=FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,100);
          imageReceiver.setImage(null,null,placeHolder == null ? ImageLocation.getForObject(thumbLocation,messageObject.photoThumbsObject) : null,"b",placeHolder != null ? new BitmapDrawable(placeHolder.bitmap) : null,0,null,messageObject,1);
        }
 else {
          imageReceiver.setImageBitmap(parentActivity.getResources().getDrawable(R.drawable.photoview_placeholder));
        }
        return;
      }
 else       if (currentAnimation != null) {
        imageReceiver.setImageBitmap(currentAnimation);
        currentAnimation.setSecondParentView(containerView);
        return;
      }
 else       if (sharedMediaType == DataQuery.MEDIA_FILE) {
        if (messageObject.canPreviewDocument()) {
          TLRPC.Document document=messageObject.getDocument();
          imageReceiver.setNeedsQualityThumb(true);
          ImageReceiver.BitmapHolder placeHolder=null;
          if (currentThumb != null && imageReceiver == centerImage) {
            placeHolder=currentThumb;
          }
          TLRPC.PhotoSize thumbLocation=messageObject != null ? FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,100) : null;
          int size=(int)(4096 / AndroidUtilities.density);
          imageReceiver.setImage(ImageLocation.getForDocument(document),String.format(Locale.US,"%d_%d",size,size),placeHolder == null ? ImageLocation.getForDocument(thumbLocation,document) : null,"b",placeHolder != null ? new BitmapDrawable(placeHolder.bitmap) : null,document.size,null,messageObject,0);
        }
 else {
          OtherDocumentPlaceholderDrawable drawable=new OtherDocumentPlaceholderDrawable(parentActivity,containerView,messageObject);
          imageReceiver.setImageBitmap(drawable);
        }
        return;
      }
    }
    int[] size=new int[1];
    ImageLocation imageLocation=getImageLocation(index,size);
    TLObject fileLocation=getFileLocation(index,size);
    if (imageLocation != null) {
      imageReceiver.setNeedsQualityThumb(true);
      ImageReceiver.BitmapHolder placeHolder=null;
      if (currentThumb != null && imageReceiver == centerImage) {
        placeHolder=currentThumb;
      }
      if (size[0] == 0) {
        size[0]=-1;
      }
      TLRPC.PhotoSize thumbLocation;
      TLObject photoObject;
      if (messageObject != null) {
        thumbLocation=FileLoader.getClosestPhotoSizeWithSize(messageObject.photoThumbs,100);
        photoObject=messageObject.photoThumbsObject;
      }
 else {
        thumbLocation=null;
        photoObject=null;
      }
      if (thumbLocation != null && thumbLocation == fileLocation) {
        thumbLocation=null;
      }
      boolean cacheOnly=messageObject != null && messageObject.isWebpage() || avatarsDialogId != 0 || isEvent;
      Object parentObject;
      if (messageObject != null) {
        parentObject=messageObject;
      }
 else       if (avatarsDialogId != 0) {
        if (avatarsDialogId > 0) {
          parentObject=MessagesController.getInstance(currentAccount).getUser(avatarsDialogId);
        }
 else {
          parentObject=MessagesController.getInstance(currentAccount).getChat(-avatarsDialogId);
        }
      }
 else {
        parentObject=null;
      }
      imageReceiver.setImage(imageLocation,null,placeHolder == null ? ImageLocation.getForObject(thumbLocation,photoObject) : null,"b",placeHolder != null ? new BitmapDrawable(placeHolder.bitmap) : null,size[0],null,parentObject,cacheOnly ? 1 : 0);
    }
 else {
      imageReceiver.setNeedsQualityThumb(true);
      if (size[0] == 0) {
        imageReceiver.setImageBitmap((Bitmap)null);
      }
 else {
        imageReceiver.setImageBitmap(parentActivity.getResources().getDrawable(R.drawable.photoview_placeholder));
      }
    }
  }
}
