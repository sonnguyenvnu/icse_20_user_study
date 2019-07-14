@Override public void onColorClicked(CarouselData carousel,int colorPosition){
  int carouselPosition=carousels.indexOf(carousel);
  ColorData colorData=carousels.get(carouselPosition).getColors().get(colorPosition);
  colorData.setPlayAnimation(!colorData.shouldPlayAnimation());
  updateController();
}
