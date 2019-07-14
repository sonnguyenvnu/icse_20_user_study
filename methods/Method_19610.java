@OnEvent(ClickEvent.class) static void onClick(ComponentContext c,@FromEvent View view){
  FavouriteButton.toggleFavouritedSync(c);
}
