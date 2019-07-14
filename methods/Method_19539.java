public RenderInfo createComponent(ComponentContext c){
  final Component component=mIsMe ? ExpandableElementMe.create(c).messageText(mMessage).timestamp(mTimestamp).seen(mSeen).forceAnimateOnAppear(mForceAnimateOnAppear).build() : ExpandableElementOther.create(c).messageText(mMessage).timestamp(mTimestamp).seen(mSeen).build();
  return ComponentRenderInfo.create().component(component).build();
}
