@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Text.create(c).text("Hello, World!").textColor(Color.RED).textSizePx(70).typeface(Typeface.DEFAULT_BOLD).build();
}
