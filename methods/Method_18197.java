@Override protected boolean shouldUpdate(Component previous,Component next){
  final ComparableDrawable previousDrawable=((DrawableComponent)previous).getDrawable();
  final ComparableDrawable nextDrawable=((DrawableComponent)next).getDrawable();
  return !previousDrawable.isEquivalentTo(nextDrawable);
}
