boolean canUsePreviousLayout(ComponentContext context){
  return ComponentsConfiguration.enableShouldCreateLayoutWithNewSizeSpec && !onShouldCreateLayoutWithNewSizeSpec(context,context.getWidthSpec(),context.getHeightSpec());
}
