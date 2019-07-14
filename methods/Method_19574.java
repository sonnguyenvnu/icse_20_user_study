@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop final DemoListActivity.DemoListDataModel model){
  return Column.create(c).paddingDip(ALL,16).child(Text.create(c).text(model.name).textSizeSp(18).build()).clickHandler(DemoListItemComponent.onClick(c)).build();
}
