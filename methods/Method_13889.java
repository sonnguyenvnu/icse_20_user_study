private void formatCsv(List<CellData> cells,StringBuffer sb){
  boolean first=true;
  for (int i=0; i < cells.size() && i < columnNames.size(); i++) {
    CellData cellData=cells.get(i);
    if (!first) {
      sb.append(',');
    }
 else {
      first=false;
    }
    sb.append("\"");
    if (cellData != null && cellData.text != null) {
      sb.append(cellData.text.replaceAll("\"","\"\""));
    }
    sb.append("\"");
  }
  sb.append("\n");
}
