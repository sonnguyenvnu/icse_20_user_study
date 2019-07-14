@Override public ReconJob createJob(Project project,int rowIndex,Row row,String columnName,Cell cell){
  StandardReconJob job=new StandardReconJob();
  List<QueryProperty> properties=new ArrayList<>();
  for (  ColumnDetail c : columnDetails) {
    int detailCellIndex=project.columnModel.getColumnByName(c.columnName).getCellIndex();
    Cell cell2=row.getCell(detailCellIndex);
    if (cell2 == null || !ExpressionUtils.isNonBlankData(cell2.value)) {
      int cellIndex=project.columnModel.getColumnByName(columnName).getCellIndex();
      RowDependency rd=project.recordModel.getRowDependency(rowIndex);
      if (rd != null && rd.cellDependencies != null) {
        int contextRowIndex=rd.cellDependencies[cellIndex].rowIndex;
        if (contextRowIndex >= 0 && contextRowIndex < project.rows.size()) {
          Row row2=project.rows.get(contextRowIndex);
          cell2=row2.getCell(detailCellIndex);
        }
      }
    }
    if (cell2 != null && ExpressionUtils.isNonBlankData(cell2.value)) {
      Object v=null;
      if (cell2.recon != null && cell2.recon.match != null) {
        Map<String,String> recon=new HashMap<>();
        recon.put("id",cell2.recon.match.id);
        recon.put("name",cell2.recon.match.name);
        v=recon;
      }
 else {
        v=cell2.value;
      }
      properties.add(new QueryProperty(c.propertyID,v));
    }
  }
  ReconQuery query=new ReconQuery(cell.value.toString(),typeID,properties,limit);
  job.text=cell.value.toString();
  try {
    job.code=ParsingUtilities.defaultWriter.writeValueAsString(query);
  }
 catch (  JsonProcessingException e) {
    e.printStackTrace();
  }
  return job;
}
