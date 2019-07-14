@OnCreateLayout static Component onCreateLayout(ComponentContext c,@State boolean favourited){
  return Row.create(c).backgroundRes(favourited ? star_on : star_off).widthDip(32).heightDip(32).clickHandler(FavouriteButton.onClick(c)).build();
}
