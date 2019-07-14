@Override protected String setHeaderImgUrl(){
  if (booksBean == null) {
    return "";
  }
  return booksBean.getImages().getMedium();
}
