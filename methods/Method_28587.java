@Override public void updateUI(String data){
  PipelineDraweeControllerBuilder controller=Fresco.newDraweeControllerBuilder();
  controller.setUri(data);
  controller.setOldController(ivPost.getController());
  controller.setControllerListener(new BaseControllerListener<ImageInfo>(){
    @Override public void onFinalImageSet(    String id,    ImageInfo imageInfo,    Animatable animatable){
      super.onFinalImageSet(id,imageInfo,animatable);
      if (imageInfo == null) {
        return;
      }
      ivPost.update(imageInfo.getWidth(),imageInfo.getHeight());
    }
  }
);
  ivPost.setController(controller.build());
}
