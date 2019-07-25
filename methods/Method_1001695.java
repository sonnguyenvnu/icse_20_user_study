private Tetris tetris(StringBounder stringBounder){
  if (tetris == null) {
    tetris=new Tetris(label.toString());
    for (    FingerImpl2 child : nail) {
      tetris.add(child.asSymetricalTee(stringBounder));
    }
    tetris.balance();
  }
  return tetris;
}
