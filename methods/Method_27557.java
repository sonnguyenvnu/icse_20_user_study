@Override public void onSubmit(@Nullable String title,@NonNull File file){
  if (file.exists()) {
    RequestBody image=RequestBody.create(MediaType.parse("image/*"),file);
    makeRestCall(ImgurProvider.getImgurService().postImage(title,image),imgurReponseModel -> {
      if (imgurReponseModel.getData() != null) {
        ImgurReponseModel.ImgurImage imageResponse=imgurReponseModel.getData();
        sendToView(view -> view.onUploaded(title == null ? imageResponse.getTitle() : title,imageResponse.getLink()));
        return;
      }
      sendToView(view -> view.onUploaded(null,null));
    }
,false);
  }
 else {
    if (getView() != null)     getView().onUploaded(null,null);
  }
}
