private void onImagePickedOrCaptured(int resultCode,Intent data){
  if (resultCode != Activity.RESULT_OK) {
    return;
  }
  List<Uri> uris=parsePickOrCaptureImageResult(data);
  mCaptureImageOutputFile=null;
  int maxUrisSize=Broadcast.MAX_IMAGES_SIZE - mImageUris.size();
  if (uris.size() > maxUrisSize) {
    ToastUtils.show(R.string.broadcast_send_add_image_too_many,getActivity());
    if (maxUrisSize <= 0) {
      return;
    }
    uris=uris.subList(0,maxUrisSize);
  }
  boolean appendingImages=!mImageUris.isEmpty();
  mImageUris.addAll(uris);
  bindAttachmentLayout(appendingImages);
  updateBottomBar();
  mChanged=true;
}
