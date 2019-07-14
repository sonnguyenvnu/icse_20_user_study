@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop ListRow row){
  return Column.create(c).paddingDip(YogaEdge.VERTICAL,8).paddingDip(YogaEdge.HORIZONTAL,32).child(Card.create(c).content(Column.create(c).marginDip(YogaEdge.ALL,32).child(TitleComponent.create(c).title(row.title)).child(PossiblyCrashingSubTitleComponent.create(c).subtitle(row.subtitle)).build()).build()).build();
}
