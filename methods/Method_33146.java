public static void smoothScrolling(ScrollPane scrollPane){
  customScrolling(scrollPane,scrollPane.vvalueProperty(),bounds -> bounds.getHeight());
}
