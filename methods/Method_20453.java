@Override protected void buildModels(List<CarouselData> carousels){
  header.title(R.string.epoxy).caption(R.string.header_subtitle);
  addButton.textRes(R.string.button_add).clickListener((model,parentView,clickedView,position) -> {
    callbacks.onAddCarouselClicked();
  }
);
  clearButton.textRes(R.string.button_clear).clickListener(v -> callbacks.onClearCarouselsClicked()).addIf(carousels.size() > 0,this);
  shuffleButton.textRes(R.string.button_shuffle).clickListener(v -> callbacks.onShuffleCarouselsClicked()).addIf(carousels.size() > 1,this);
  changeColorsButton.textRes(R.string.button_change).clickListener(v -> callbacks.onChangeAllColorsClicked()).addIf(carousels.size() > 0,this);
  for (int i=0; i < carousels.size(); i++) {
    CarouselData carousel=carousels.get(i);
    add(new CarouselModelGroup(carousel,callbacks));
  }
}
