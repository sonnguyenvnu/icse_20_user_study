protected void extendRow(Row row,DataExtension dataExtension,int extensionRowIndex,Map<String,Recon> reconMap){
  Object[] values=dataExtension.data[extensionRowIndex];
  for (int c=0; c < values.length; c++) {
    Object value=values[c];
    Cell cell=null;
    if (value instanceof ReconCandidate) {
      ReconCandidate rc=(ReconCandidate)value;
      Recon recon;
      if (reconMap.containsKey(rc.id)) {
        recon=reconMap.get(rc.id);
      }
 else {
        recon=new Recon(_historyEntryID,_identifierSpace,_schemaSpace);
        recon.addCandidate(rc);
        recon.service=_service;
        recon.match=rc;
        recon.matchRank=0;
        recon.judgment=Judgment.Matched;
        recon.judgmentAction="auto";
        recon.judgmentBatchSize=1;
        reconMap.put(rc.id,recon);
      }
      cell=new Cell(rc.name,recon);
    }
 else {
      cell=new Cell((Serializable)value,null);
    }
    row.setCell(_firstNewCellIndex + c,cell);
  }
}
