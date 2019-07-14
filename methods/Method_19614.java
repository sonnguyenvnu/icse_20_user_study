@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop final Artist artist){
  return Column.create(c).child(Column.create(c).child(FeedImageComponent.create(c).images(artist.images)).child(TitleComponent.create(c).title(artist.name)).child(ActionsComponent.create(c))).child(FooterComponent.create(c).text(artist.biography)).build();
}
