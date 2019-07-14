private void continueSendingWithImages(){
  if (mUploadedImageUrls.size() < mImageUris.size()) {
    Context context=getContext();
    String notificationText=context.getString(R.string.broadcast_sending_notification_text_uploading_images_format,mUploadedImageUrls.size() + 1,mImageUris.size());
    startForeground(notificationText);
    ApiRequest<UploadedImage> request=ApiService.getInstance().uploadBroadcastImage(mImageUris.get(mUploadedImageUrls.size()),context);
    request.enqueue(new ApiRequest.Callback<UploadedImage>(){
      @Override public void onResponse(      UploadedImage response){
        onImageUploadSuccess(response);
      }
      @Override public void onErrorResponse(      ApiError error){
        onErrorWithImages(error);
      }
    }
);
    mRequest=request;
  }
 else {
    startForeground(getContext().getString(R.string.broadcast_sending_notification_text_sending));
    ApiRequest<Broadcast> request=ApiService.getInstance().sendBroadcast(mText,mUploadedImageUrls,null,null);
    request.enqueue(new ApiRequest.Callback<Broadcast>(){
      @Override public void onResponse(      Broadcast response){
        onSuccessWithImages(response);
      }
      @Override public void onErrorResponse(      ApiError error){
        onErrorWithImages(error);
      }
    }
);
    mRequest=request;
  }
}
