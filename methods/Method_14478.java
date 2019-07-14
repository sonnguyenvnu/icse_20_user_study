static public Change load(LineNumberReader reader,Pool pool) throws Exception {
  ReconConfig newReconConfig=null;
  ReconStats newReconStats=null;
  ReconConfig oldReconConfig=null;
  ReconStats oldReconStats=null;
  String commonColumnName=null;
  CellChange[] cellChanges=null;
  String line;
  while ((line=reader.readLine()) != null && !"/ec/".equals(line)) {
    int equal=line.indexOf('=');
    CharSequence field=line.subSequence(0,equal);
    String value=line.substring(equal + 1);
    if ("newReconConfig".equals(field)) {
      if (value.length() > 0) {
        newReconConfig=ReconConfig.reconstruct(value);
      }
    }
 else     if ("newReconStats".equals(field)) {
      if (value.length() > 0) {
        newReconStats=ParsingUtilities.mapper.readValue(value,ReconStats.class);
      }
    }
 else     if ("oldReconConfig".equals(field)) {
      if (value.length() > 0) {
        oldReconConfig=ReconConfig.reconstruct(value);
      }
    }
 else     if ("oldReconStats".equals(field)) {
      if (value.length() > 0) {
        oldReconStats=ParsingUtilities.mapper.readValue(value,ReconStats.class);
      }
    }
 else     if ("commonColumnName".equals(field)) {
      commonColumnName=value;
    }
 else     if ("cellChangeCount".equals(field)) {
      int cellChangeCount=Integer.parseInt(value);
      cellChanges=new CellChange[cellChangeCount];
      for (int i=0; i < cellChangeCount; i++) {
        cellChanges[i]=CellChange.load(reader,pool);
      }
    }
  }
  ReconChange change=new ReconChange(cellChanges,commonColumnName,newReconConfig,newReconStats);
  change._oldReconConfig=oldReconConfig;
  change._oldReconStats=oldReconStats;
  return change;
}
