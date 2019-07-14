private void onImageUploadSuccess(UploadedImage uploadedImage){
  mUploadedImageUrls.add(uploadedImage.url);
  continueSendingWithImages();
}
