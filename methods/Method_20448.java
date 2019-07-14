@Override public void onShuffleCarouselColorsClicked(CarouselData carousel){
  Collections.shuffle(carousel.getColors());
  updateController();
}
