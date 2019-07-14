static public ReconStats create(Project project,int cellIndex){
  int nonBlanks=0;
  int newTopics=0;
  int matchedTopics=0;
  for (  Row row : project.rows) {
    Cell cell=row.getCell(cellIndex);
    if (cell != null && ExpressionUtils.isNonBlankData(cell.value)) {
      nonBlanks++;
      if (cell.recon != null) {
        if (cell.recon.judgment == Judgment.New) {
          newTopics++;
        }
 else         if (cell.recon.judgment == Judgment.Matched) {
          matchedTopics++;
        }
      }
    }
  }
  return new ReconStats(nonBlanks,newTopics,matchedTopics);
}
