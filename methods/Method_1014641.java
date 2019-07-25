@Override protected boolean validate(PictureRenderData data){
  return (null != data.getData() || null != data.getPath());
}
