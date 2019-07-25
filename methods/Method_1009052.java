private static void handle(Map<String,ShapeWrapper> placeholders,String placeholderType,Shape sp){
  Shape layoutShape=null;
  if (placeholders.get(placeholderType) != null) {
    log.debug("Got it..");
    layoutShape=placeholders.get(placeholderType).getSp();
  }
 else {
    log.debug("Missing..");
    return;
  }
  handleNvSpPr(sp.getNvSpPr(),layoutShape.getNvSpPr());
  handleSpPr(sp.getSpPr(),layoutShape.getSpPr());
  handleTxBody(sp.getTxBody(),layoutShape.getTxBody());
}
