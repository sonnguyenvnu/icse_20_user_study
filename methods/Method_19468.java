@OnMount static void onMount(ComponentContext c,TransparencyEnabledCardClipDrawable cardClipDrawable,@Prop(optional=true,resType=ResType.COLOR) int cardBackgroundColor,@Prop(optional=true,resType=ResType.DIMEN_OFFSET) float cornerRadius){
  cardClipDrawable.setBackgroundColor(cardBackgroundColor);
  cardClipDrawable.setCornerRadius(cornerRadius);
}
