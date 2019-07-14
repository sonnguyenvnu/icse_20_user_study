@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop Component content,@Prop(optional=true,resType=ResType.COLOR) int cardBackgroundColor,@Prop(optional=true,resType=ResType.COLOR) int clippingColor,@Prop(optional=true,resType=ResType.COLOR) int shadowStartColor,@Prop(optional=true,resType=ResType.COLOR) int shadowEndColor,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) float cornerRadius,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) float elevation,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) int shadowBottomOverride,@Prop(optional=true) boolean disableClipTopLeft,@Prop(optional=true) boolean disableClipTopRight,@Prop(optional=true) boolean disableClipBottomLeft,@Prop(optional=true) boolean disableClipBottomRight){
  final Resources resources=c.getAndroidContext().getResources();
  if (cornerRadius == -1) {
    cornerRadius=pixels(resources,DEFAULT_CORNER_RADIUS_DP);
  }
  if (elevation == -1) {
    elevation=pixels(resources,DEFAULT_SHADOW_SIZE_DP);
  }
  final int shadowTop=getShadowTop(elevation);
  final int shadowBottom=shadowBottomOverride == -1 ? getShadowBottom(elevation) : shadowBottomOverride;
  final int shadowHorizontal=getShadowHorizontal(elevation);
  return Column.create(c).child(Column.create(c).marginPx(HORIZONTAL,shadowHorizontal).marginPx(TOP,disableClipTopLeft && disableClipTopRight ? 0 : shadowTop).marginPx(BOTTOM,disableClipBottomLeft && disableClipBottomRight ? 0 : shadowBottom).backgroundColor(cardBackgroundColor).child(content).child(CardClip.create(c).clippingColor(clippingColor).cornerRadiusPx(cornerRadius).positionType(ABSOLUTE).positionPx(ALL,0).disableClipTopLeft(disableClipTopLeft).disableClipTopRight(disableClipTopRight).disableClipBottomLeft(disableClipBottomLeft).disableClipBottomRight(disableClipBottomRight))).child(elevation > 0 ? CardShadow.create(c).shadowStartColor(shadowStartColor).shadowEndColor(shadowEndColor).cornerRadiusPx(cornerRadius).shadowSizePx(elevation).hideTopShadow(disableClipTopLeft && disableClipTopRight).hideBottomShadow(disableClipBottomLeft && disableClipBottomRight).positionType(ABSOLUTE).positionPx(ALL,0) : null).build();
}
