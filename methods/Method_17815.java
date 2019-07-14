static boolean isLayoutSpecWithSizeSpec(@Nullable Component component){
  return (isLayoutSpec(component) && component.canMeasure());
}
