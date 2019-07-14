private void addColorToCarousel(CarouselData carousel){
  List<ColorData> colors=carousel.getColors();
  colors.add(0,new ColorData(randomColor(),colors.size()));
}
