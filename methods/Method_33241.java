private void createChip(T item){
  JFXChip<T> chip=null;
  try {
    if (getSkinnable().getChipFactory() != null) {
      chip=getSkinnable().getChipFactory().apply(getSkinnable(),item);
    }
 else {
      chip=new JFXDefaultChip<T>(getSkinnable(),item);
    }
  }
 catch (  Exception e) {
    throw new RuntimeException("can't create chip for item '" + item + "' make sure to override the string converter and return null if text input is not valid.",e);
  }
  int size=root.getChildren().size();
  root.getChildren().add(size - 1,chip);
}
