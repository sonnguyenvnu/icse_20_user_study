private static List<EpoxyModel<?>> buildModels(CarouselData carousel,AdapterCallbacks callbacks){
  List<ColorData> colors=carousel.getColors();
  ArrayList<EpoxyModel<?>> models=new ArrayList<>();
  models.add(new ImageButtonModel_().id("add").imageRes(R.drawable.ic_add_circle).clickListener((model,parentView,clickedView,position) -> callbacks.onAddColorToCarouselClicked(carousel)));
  models.add(new ImageButtonModel_().id("delete").imageRes(R.drawable.ic_delete).clickListener(v -> callbacks.onClearCarouselClicked(carousel)).show(colors.size() > 0));
  models.add(new ImageButtonModel_().id("change").imageRes(R.drawable.ic_change).clickListener(v -> callbacks.onChangeCarouselColorsClicked(carousel)).show(colors.size() > 0));
  models.add(new ImageButtonModel_().id("shuffle").imageRes(R.drawable.ic_shuffle).clickListener(v -> callbacks.onShuffleCarouselColorsClicked(carousel)).show(colors.size() > 1));
  List<ColorModel_> colorModels=new ArrayList<>();
  for (  ColorData colorData : colors) {
    colorModels.add(new ColorModel_().id(colorData.getId(),carousel.getId()).color(colorData.getColorInt()).playAnimation(colorData.shouldPlayAnimation()).clickListener((model,parentView,clickedView,position) -> {
      callbacks.onColorClicked(carousel,position);
    }
));
  }
  models.add(new GridCarouselModel_().id("carousel").models(colorModels));
  return models;
}
