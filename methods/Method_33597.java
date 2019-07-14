@Override protected String setHeaderImgUrl(){
  if (filmItemBean == null) {
    return "";
  }
  return filmItemBean.getImg();
}
