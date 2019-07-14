private void initNavigation(){
  setOnKeyPressed(ke -> {
switch (ke.getCode()) {
case SPACE:
case ENTER:
      if (focusedSquare != null) {
        focusedSquare.selectColor(ke);
      }
    ke.consume();
  break;
default :
}
}
);
}
