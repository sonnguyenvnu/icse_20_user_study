@Override protected String setHeaderImgUrl(){
  if (subjectsBean == null) {
    return "";
  }
  return subjectsBean.getImages().getMedium();
}
