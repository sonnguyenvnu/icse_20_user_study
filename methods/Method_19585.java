@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop Component child,@State Optional<Exception> error){
  if (error.isPresent()) {
    return Column.create(c).marginDip(YogaEdge.ALL,16).child(DebugErrorComponent.create(c).message("Error Boundary").throwable(error.get()).build()).build();
  }
  return child;
}
