@Override public void onChangeAllColorsClicked(){
  for (  CarouselData carouselData : carousels) {
    for (    ColorData colorData : carouselData.getColors()) {
      colorData.setColorInt(randomColor());
    }
  }
  updateController();
}
