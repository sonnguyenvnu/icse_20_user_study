@Override public void onAddCarouselClicked(){
  CarouselData carousel=new CarouselData(carousels.size(),new ArrayList<>());
  addColorToCarousel(carousel);
  carousels.add(0,carousel);
  updateController();
}
