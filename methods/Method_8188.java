private int getMessageType(MessageObject messageObject){
  if (messageObject == null) {
    return -1;
  }
  if (messageObject.type == 6) {
    return -1;
  }
 else   if (messageObject.type == 10 || messageObject.type == 11 || messageObject.type == 16) {
    if (messageObject.getId() == 0) {
      return -1;
    }
    return 1;
  }
 else {
    if (messageObject.isVoice()) {
      return 2;
    }
 else     if (messageObject.isSticker()) {
      TLRPC.InputStickerSet inputStickerSet=messageObject.getInputStickerSet();
      if (inputStickerSet instanceof TLRPC.TL_inputStickerSetID) {
        if (!DataQuery.getInstance(currentAccount).isStickerPackInstalled(inputStickerSet.id)) {
          return 7;
        }
      }
 else       if (inputStickerSet instanceof TLRPC.TL_inputStickerSetShortName) {
        if (!DataQuery.getInstance(currentAccount).isStickerPackInstalled(inputStickerSet.short_name)) {
          return 7;
        }
      }
    }
 else     if ((!messageObject.isRoundVideo() || messageObject.isRoundVideo() && BuildVars.DEBUG_VERSION) && (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto || messageObject.getDocument() != null || messageObject.isMusic() || messageObject.isVideo())) {
      boolean canSave=false;
      if (messageObject.messageOwner.attachPath != null && messageObject.messageOwner.attachPath.length() != 0) {
        File f=new File(messageObject.messageOwner.attachPath);
        if (f.exists()) {
          canSave=true;
        }
      }
      if (!canSave) {
        File f=FileLoader.getPathToMessage(messageObject.messageOwner);
        if (f.exists()) {
          canSave=true;
        }
      }
      if (canSave) {
        if (messageObject.getDocument() != null) {
          String mime=messageObject.getDocument().mime_type;
          if (mime != null) {
            if (messageObject.getDocumentName().toLowerCase().endsWith("attheme")) {
              return 10;
            }
 else             if (mime.endsWith("/xml")) {
              return 5;
            }
 else             if (mime.endsWith("/png") || mime.endsWith("/jpg") || mime.endsWith("/jpeg")) {
              return 6;
            }
          }
        }
        return 4;
      }
    }
 else     if (messageObject.type == 12) {
      return 8;
    }
 else     if (messageObject.isMediaEmpty()) {
      return 3;
    }
    return 2;
  }
}
