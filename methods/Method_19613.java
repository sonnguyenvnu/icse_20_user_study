@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop final Artist artist){
  return Column.create(c).paddingDip(VERTICAL,8).paddingDip(HORIZONTAL,16).child(Card.create(c).content(FeedItemComponent.create(c).artist(artist))).build();
}
