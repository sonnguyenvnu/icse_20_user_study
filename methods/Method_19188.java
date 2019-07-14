@OnMount static void onMount(ComponentContext c,CardClipDrawable cardClipDrawable,@Prop(optional=true,resType=ResType.COLOR) int clippingColor,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) float cornerRadius,@Prop(optional=true) boolean disableClipTopLeft,@Prop(optional=true) boolean disableClipTopRight,@Prop(optional=true) boolean disableClipBottomLeft,@Prop(optional=true) boolean disableClipBottomRight){
  cardClipDrawable.setClippingColor(clippingColor);
  cardClipDrawable.setCornerRadius(cornerRadius);
  int clipEdge=(disableClipTopLeft ? TOP_LEFT : NONE) | (disableClipTopRight ? TOP_RIGHT : NONE) | (disableClipBottomLeft ? BOTTOM_LEFT : NONE) | (disableClipBottomRight ? BOTTOM_RIGHT : NONE);
  cardClipDrawable.setDisableClip(clipEdge);
}
