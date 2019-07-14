/** 
 * {@inheritDoc}
 */
@Override public void replaceCells(Card parent,List<BaseCell> cells){
  if (parent != null && cells != null) {
    parent.setCells(cells);
    parent.notifyDataChange();
  }
}
