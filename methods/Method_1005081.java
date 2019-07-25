@Override public List<LazyElementCell> process(final List<LazyElementCell> elementCells){
  if (elementCells.size() <= 1) {
    return elementCells;
  }
  final List<LazyElementCell> output=new ArrayList<>();
  ElementAggregator aggregator=null;
  Properties aggregatedProperties=null;
  LazyElementCell firstElementCell=null;
  for (  final LazyElementCell elementCell : elementCells) {
    if (elementCell.isDeleted()) {
      continue;
    }
    if (!aggregatedGroups.contains(elementCell.getGroup())) {
      if (null != firstElementCell) {
        output(firstElementCell,aggregatedProperties,output);
        firstElementCell=null;
      }
      output(elementCell,null,output);
      aggregatedProperties=null;
      aggregator=null;
    }
 else     if (null == firstElementCell) {
      firstElementCell=elementCell;
      aggregatedProperties=null;
      aggregator=null;
    }
 else     if (!HBaseUtil.compareKeys(firstElementCell.getCell(),elementCell.getCell())) {
      output(firstElementCell,aggregatedProperties,output);
      firstElementCell=elementCell;
      aggregatedProperties=null;
      aggregator=null;
    }
 else {
      final String group=firstElementCell.getGroup();
      if (null == aggregator) {
        aggregator=schema.getElement(group).getIngestAggregator();
        aggregatedProperties=firstElementCell.getElement().getProperties();
      }
      final Properties properties=elementCell.getElement().getProperties();
      aggregatedProperties=aggregator.apply(properties,aggregatedProperties);
    }
  }
  output(firstElementCell,aggregatedProperties,output);
  return output;
}
