@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop final Uri profilePicture,@Prop final Uri mainPicture,@Prop final String title){
  return Column.create(c).paddingDip(ALL,16).child(Row.create(c).child(FrescoVitoImage.create(c).uri(profilePicture).imageOptions(PROFILE_IMAGE_OPTIONS).widthDip(52)).child(Text.create(c).text(title).textSizeSp(24).alignSelf(CENTER).paddingDip(START,8)).paddingDip(BOTTOM,8)).child(FrescoVitoImage.create(c).uri(mainPicture).imageOptions(IMAGE_OPTIONS)).build();
}
