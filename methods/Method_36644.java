@CellRender public void postBindView(BaseCell cell){
  String imgUrl=cell.optStringParam("imgUrl");
  float ratioFromUrl=Utils.getImageRatio(imgUrl);
  setRatio(ratioFromUrl);
  if (cell.style != null) {
    if (!Float.isNaN(cell.style.aspectRatio)) {
      setRatio(cell.style.aspectRatio,RatioImageView.PRIORITY_HIGH);
    }
  }
  ImageUtils.doLoadImageUrl(this,imgUrl);
  setOnClickListener(cell);
}
