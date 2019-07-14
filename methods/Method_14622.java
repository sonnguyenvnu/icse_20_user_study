protected RowVisitor createRowVisitor(Project project,List<Integer> rowIndices) throws Exception {
  return new RowVisitor(){
    public RowVisitor init(    List<Integer> rowIndices){
      this.rowIndices=rowIndices;
      return this;
    }
    @Override public void start(    Project project){
    }
    @Override public void end(    Project project){
    }
    @Override public boolean visit(    Project project,    int rowIndex,    Row row){
      rowIndices.add(rowIndex);
      return false;
    }
  }
.init(rowIndices);
}
