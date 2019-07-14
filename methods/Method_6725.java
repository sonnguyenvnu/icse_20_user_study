public void generateThumbs(boolean update){
  if (messageOwner instanceof TLRPC.TL_messageService) {
    if (messageOwner.action instanceof TLRPC.TL_messageActionChatEditPhoto) {
      TLRPC.Photo photo=messageOwner.action.photo;
      if (!update) {
        photoThumbs=new ArrayList<>(photo.sizes);
      }
 else       if (photoThumbs != null && !photoThumbs.isEmpty()) {
        for (int a=0; a < photoThumbs.size(); a++) {
          TLRPC.PhotoSize photoObject=photoThumbs.get(a);
          for (int b=0; b < photo.sizes.size(); b++) {
            TLRPC.PhotoSize size=photo.sizes.get(b);
            if (size instanceof TLRPC.TL_photoSizeEmpty) {
              continue;
            }
            if (size.type.equals(photoObject.type)) {
              photoObject.location=size.location;
              break;
            }
          }
        }
      }
      if (photo.dc_id != 0) {
        for (int a=0, N=photoThumbs.size(); a < N; a++) {
          TLRPC.FileLocation location=photoThumbs.get(a).location;
          location.dc_id=photo.dc_id;
          location.file_reference=photo.file_reference;
        }
      }
      photoThumbsObject=messageOwner.action.photo;
    }
  }
 else   if (messageOwner.media != null && !(messageOwner.media instanceof TLRPC.TL_messageMediaEmpty)) {
    if (messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
      TLRPC.Photo photo=messageOwner.media.photo;
      if (!update || photoThumbs != null && photoThumbs.size() != photo.sizes.size()) {
        photoThumbs=new ArrayList<>(photo.sizes);
      }
 else       if (photoThumbs != null && !photoThumbs.isEmpty()) {
        for (int a=0; a < photoThumbs.size(); a++) {
          TLRPC.PhotoSize photoObject=photoThumbs.get(a);
          if (photoObject == null) {
            continue;
          }
          for (int b=0; b < photo.sizes.size(); b++) {
            TLRPC.PhotoSize size=photo.sizes.get(b);
            if (size == null || size instanceof TLRPC.TL_photoSizeEmpty) {
              continue;
            }
            if (size.type.equals(photoObject.type)) {
              photoObject.location=size.location;
              break;
            }
          }
        }
      }
      photoThumbsObject=messageOwner.media.photo;
    }
 else     if (messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
      TLRPC.Document document=messageOwner.media.document;
      if (isDocumentHasThumb(document)) {
        if (!update || photoThumbs == null) {
          photoThumbs=new ArrayList<>();
          photoThumbs.addAll(document.thumbs);
        }
 else         if (photoThumbs != null && !photoThumbs.isEmpty()) {
          updatePhotoSizeLocations(photoThumbs,document.thumbs);
        }
        photoThumbsObject=document;
      }
    }
 else     if (messageOwner.media instanceof TLRPC.TL_messageMediaGame) {
      TLRPC.Document document=messageOwner.media.game.document;
      if (document != null) {
        if (isDocumentHasThumb(document)) {
          if (!update) {
            photoThumbs=new ArrayList<>();
            photoThumbs.addAll(document.thumbs);
          }
 else           if (photoThumbs != null && !photoThumbs.isEmpty()) {
            updatePhotoSizeLocations(photoThumbs,document.thumbs);
          }
          photoThumbsObject=document;
        }
      }
      TLRPC.Photo photo=messageOwner.media.game.photo;
      if (photo != null) {
        if (!update || photoThumbs2 == null) {
          photoThumbs2=new ArrayList<>(photo.sizes);
        }
 else         if (!photoThumbs2.isEmpty()) {
          updatePhotoSizeLocations(photoThumbs2,photo.sizes);
        }
        photoThumbsObject2=photo;
      }
      if (photoThumbs == null && photoThumbs2 != null) {
        photoThumbs=photoThumbs2;
        photoThumbs2=null;
        photoThumbsObject=photoThumbsObject2;
        photoThumbsObject2=null;
      }
    }
 else     if (messageOwner.media instanceof TLRPC.TL_messageMediaWebPage) {
      TLRPC.Photo photo=messageOwner.media.webpage.photo;
      TLRPC.Document document=messageOwner.media.webpage.document;
      if (photo != null) {
        if (!update || photoThumbs == null) {
          photoThumbs=new ArrayList<>(photo.sizes);
        }
 else         if (!photoThumbs.isEmpty()) {
          updatePhotoSizeLocations(photoThumbs,photo.sizes);
        }
        photoThumbsObject=photo;
      }
 else       if (document != null) {
        if (isDocumentHasThumb(document)) {
          if (!update) {
            photoThumbs=new ArrayList<>();
            photoThumbs.addAll(document.thumbs);
          }
 else           if (photoThumbs != null && !photoThumbs.isEmpty()) {
            updatePhotoSizeLocations(photoThumbs,document.thumbs);
          }
          photoThumbsObject=document;
        }
      }
    }
  }
}
