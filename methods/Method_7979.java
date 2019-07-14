@Override public void onSuccessDownload(String fileName){
  if (documentAttachType == DOCUMENT_ATTACH_TYPE_AUDIO || documentAttachType == DOCUMENT_ATTACH_TYPE_MUSIC) {
    updateButtonState(false,true,false);
    updateWaveform();
  }
 else {
    if (drawVideoImageButton) {
      videoRadialProgress.setProgress(1,true);
    }
 else {
      radialProgress.setProgress(1,true);
    }
    boolean startedAutoplay=false;
    if (!currentMessageObject.needDrawBluredPreview() && !autoPlayingMedia && documentAttach != null) {
      if (documentAttachType == DOCUMENT_ATTACH_TYPE_ROUND) {
        photoImage.setImage(ImageLocation.getForDocument(documentAttach),ImageLoader.AUTOPLAY_FILTER,ImageLocation.getForObject(currentPhotoObject,photoParentObject),currentPhotoObject instanceof TLRPC.TL_photoStrippedSize || currentPhotoObject != null && "s".equals(currentPhotoObject.type) ? currentPhotoFilterThumb : currentPhotoFilter,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),currentPhotoFilterThumb,null,documentAttach.size,null,currentMessageObject,0);
        photoImage.setAllowStartAnimation(true);
        photoImage.startAnimation();
        autoPlayingMedia=true;
      }
 else       if (SharedConfig.autoplayVideo && documentAttachType == DOCUMENT_ATTACH_TYPE_VIDEO && (currentPosition == null || (currentPosition.flags & MessageObject.POSITION_FLAG_LEFT) != 0 && (currentPosition.flags & MessageObject.POSITION_FLAG_RIGHT) != 0)) {
        animatingNoSound=2;
        photoImage.setImage(ImageLocation.getForDocument(documentAttach),ImageLoader.AUTOPLAY_FILTER,ImageLocation.getForObject(currentPhotoObject,photoParentObject),currentPhotoObject instanceof TLRPC.TL_photoStrippedSize || currentPhotoObject != null && "s".equals(currentPhotoObject.type) ? currentPhotoFilterThumb : currentPhotoFilter,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),currentPhotoFilterThumb,null,documentAttach.size,null,currentMessageObject,0);
        if (!PhotoViewer.isPlayingMessage(currentMessageObject)) {
          photoImage.setAllowStartAnimation(true);
          photoImage.startAnimation();
        }
 else {
          photoImage.setAllowStartAnimation(false);
        }
        autoPlayingMedia=true;
      }
 else       if (documentAttachType == DOCUMENT_ATTACH_TYPE_GIF) {
        photoImage.setImage(ImageLocation.getForDocument(documentAttach),ImageLoader.AUTOPLAY_FILTER,ImageLocation.getForObject(currentPhotoObject,photoParentObject),currentPhotoObject instanceof TLRPC.TL_photoStrippedSize || currentPhotoObject != null && "s".equals(currentPhotoObject.type) ? currentPhotoFilterThumb : currentPhotoFilter,ImageLocation.getForObject(currentPhotoObjectThumb,photoParentObject),currentPhotoFilterThumb,null,documentAttach.size,null,currentMessageObject,0);
        if (SharedConfig.autoplayGifs) {
          photoImage.setAllowStartAnimation(true);
          photoImage.startAnimation();
        }
 else {
          photoImage.setAllowStartAnimation(false);
          photoImage.stopAnimation();
        }
        autoPlayingMedia=true;
      }
    }
    if (currentMessageObject.type == 0) {
      if (!autoPlayingMedia && documentAttachType == DOCUMENT_ATTACH_TYPE_GIF && currentMessageObject.gifState != 1) {
        buttonState=2;
        didPressButton(true,false);
      }
 else       if (!photoNotSet) {
        updateButtonState(false,true,false);
      }
 else {
        setMessageObject(currentMessageObject,currentMessagesGroup,pinnedBottom,pinnedTop);
      }
    }
 else {
      if (!photoNotSet) {
        updateButtonState(false,true,false);
      }
      if (photoNotSet) {
        setMessageObject(currentMessageObject,currentMessagesGroup,pinnedBottom,pinnedTop);
      }
    }
  }
}
