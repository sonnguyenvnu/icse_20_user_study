public SmartArtDataHierarchy convert(){
  SmartArtDataHierarchy smartArtDataHierarchy=factory.createSmartArtDataHierarchy();
  CTPt docPt=ptList.getPt().get(0);
  if (docPt.getPrSet() != null && docPt.getPrSet().getLoTypeId() != null) {
    smartArtDataHierarchy.setLoTypeId(docPt.getPrSet().getLoTypeId());
  }
 else {
    log.error("Couldn't read @loTypeId");
  }
  org.opendope.SmartArt.dataHierarchy.List docList=factory.createList();
  org.opendope.SmartArt.dataHierarchy.ListItem listItem=factory.createListItem();
  docList.getListItem().add(listItem);
  listItem.setId(docPt.getModelId());
  listItem.setDepth(0);
  processChildrenOf(docPt,listItem);
  smartArtDataHierarchy.setList(docList);
  smartArtDataHierarchy.setImages(images);
  smartArtDataHierarchy.setTexts(texts);
  return smartArtDataHierarchy;
}
