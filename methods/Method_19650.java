@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop String text1,@Prop String text2){
  return Column.create(c).child(Text.create(c).text(text1).textSizeDip(50)).child(Text.create(c).text(text2).textColorRes(android.R.color.holo_green_dark).textSizeSp(30)).build();
}
