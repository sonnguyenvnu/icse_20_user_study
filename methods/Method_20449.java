@Override public void onChangeCarouselColorsClicked(CarouselData carousel){
  for (  ColorData colorData : carousel.getColors()) {
    colorData.setColorInt(randomColor());
  }
  updateController();
}
