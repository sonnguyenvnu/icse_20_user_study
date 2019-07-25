private void output(final LazyElementCell elementCell,final Properties aggregatedProperties,final List<LazyElementCell> output){
  if (null == aggregatedProperties) {
    if (null != elementCell) {
      output.add(elementCell);
    }
  }
 else {
    try {
      final Cell firstCell=elementCell.getCell();
      final Element element=elementCell.getElement();
      element.copyProperties(aggregatedProperties);
      final Cell aggregatedCell=CellUtil.createCell(CellUtil.cloneRow(firstCell),CellUtil.cloneFamily(firstCell),CellUtil.cloneQualifier(firstCell),serialisation.getTimestamp(element),firstCell.getTypeByte(),serialisation.getValue(element),CellUtil.getTagArray(firstCell),0);
      elementCell.setCell(aggregatedCell);
      elementCell.setElement(element);
      output.add(elementCell);
    }
 catch (    final SerialisationException e) {
      throw new RuntimeException(e);
    }
  }
}
