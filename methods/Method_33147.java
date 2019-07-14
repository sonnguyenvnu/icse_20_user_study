public static void smoothHScrolling(ScrollPane scrollPane){
  customScrolling(scrollPane,scrollPane.hvalueProperty(),bounds -> bounds.getWidth());
}
