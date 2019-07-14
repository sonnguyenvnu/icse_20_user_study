@OnMount static void onMount(ComponentContext context,CardShadowDrawable cardShadowDrawable,@Prop(optional=true,resType=ResType.COLOR) int shadowStartColor,@Prop(optional=true,resType=ResType.COLOR) int shadowEndColor,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) float cornerRadius,@Prop(optional=true,resType=ResType.DIMEN_SIZE) float shadowSize,@Prop(optional=true) boolean hideTopShadow,@Prop(optional=true) boolean hideBottomShadow){
  cardShadowDrawable.setShadowStartColor(shadowStartColor);
  cardShadowDrawable.setShadowEndColor(shadowEndColor);
  cardShadowDrawable.setCornerRadius(cornerRadius);
  cardShadowDrawable.setShadowSize(shadowSize);
  cardShadowDrawable.setHideTopShadow(hideTopShadow);
  cardShadowDrawable.setHideBottomShadow(hideBottomShadow);
}
