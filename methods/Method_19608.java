@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop final Decade decade){
  return Row.create(c).alignItems(YogaAlign.CENTER).paddingDip(YogaEdge.ALL,16).child(Row.create(c).heightPx(1).backgroundColor(0xFFAAAAAA).flex(1)).child(Text.create(c).text(String.valueOf(decade.year)).textSizeDip(14).textColor(0xFFAAAAAA).marginDip(YogaEdge.HORIZONTAL,10).flex(0)).child(Row.create(c).heightPx(1).backgroundColor(0xFFAAAAAA).flex(1)).backgroundColor(0xFFFAFAFA).build();
}
