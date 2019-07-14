@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Column.create(c).child(Text.create(c).text("Hello, World!").textSizeDip(50)).child(Text.create(c).text("Hello, world 2!").textColorRes(android.R.color.holo_green_dark).textSizeSp(30)).build();
}
