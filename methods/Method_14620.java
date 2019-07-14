protected RowVisitor createRowVisitor(Project project,List<Change> changes) throws Exception {
  return new RowVisitor(){
    public RowVisitor init(    List<Change> changes){
      this.changes=changes;
      return this;
    }
    @Override public void start(    Project project){
    }
    @Override public void end(    Project project){
    }
    @Override public boolean visit(    Project project,    int rowIndex,    Row row){
      if (row.flagged != _flagged) {
        RowFlagChange change=new RowFlagChange(rowIndex,_flagged);
        changes.add(change);
      }
      return false;
    }
  }
.init(changes);
}
