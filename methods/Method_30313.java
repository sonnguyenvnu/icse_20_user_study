public static Intent makeIntent(CollectableItem item,Context context){
  if (item instanceof Book) {
    return BookActivity.makeIntent((Book)item,context);
  }
 else   if (item instanceof SimpleBook) {
    return BookActivity.makeIntent((SimpleBook)item,context);
  }
 else   if (item instanceof Movie) {
    return MovieActivity.makeIntent((Movie)item,context);
  }
 else   if (item instanceof SimpleMovie) {
    return MovieActivity.makeIntent((SimpleMovie)item,context);
  }
 else   if (item instanceof Music) {
    return MusicActivity.makeIntent((Music)item,context);
  }
 else   if (item instanceof SimpleMusic) {
    return MusicActivity.makeIntent((SimpleMusic)item,context);
  }
 else   if (item instanceof Game) {
    return GameActivity.makeIntent((Game)item,context);
  }
 else   if (item instanceof SimpleGame) {
    return GameActivity.makeIntent((SimpleGame)item,context);
  }
 else {
switch (item.getType()) {
case BOOK:
      return BookActivity.makeIntent(item.id,context);
case GAME:
    return GameActivity.makeIntent(item.id,context);
case MOVIE:
case TV:
  return MovieActivity.makeIntent(item.id,context);
case MUSIC:
return MusicActivity.makeIntent(item.id,context);
default :
return null;
}
}
}
