public Component createComponent(ComponentContext c){
  return ErrorBoundary.create(c).child(ListRowComponent.create(c).row(this).build()).build();
}
