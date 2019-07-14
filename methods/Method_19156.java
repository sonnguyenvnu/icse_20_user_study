@OnTrigger(ScrollEvent.class) static void onScroll(ComponentContext c,@FromTrigger int position,@FromTrigger boolean animate,@State SectionTree sectionTree){
  sectionTree.requestFocusOnRoot(position);
}
