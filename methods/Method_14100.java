@Override public void computeClusters(Engine engine){
  BinningRowVisitor visitor=new BinningRowVisitor(_keyer,_parameters);
  FilteredRows filteredRows=engine.getAllFilteredRows();
  filteredRows.accept(_project,visitor);
  Map<String,Map<String,Integer>> map=visitor.getMap();
  _clusters=new ArrayList<Map<String,Integer>>(map.values());
  Collections.sort(_clusters,new SizeComparator());
}
