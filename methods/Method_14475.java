protected void switchRecons(Project project,Map<Long,Recon> reconMap){
synchronized (project) {
    HashSet<String> flushedColumn=new HashSet<String>();
    for (    Row row : project.rows) {
      for (int c=0; c < row.cells.size(); c++) {
        Cell cell=row.cells.get(c);
        if (cell != null && cell.recon != null) {
          Recon recon=cell.recon;
          if (reconMap.containsKey(recon.id)) {
            String columnName=project.columnModel.getColumnByCellIndex(c).getName();
            if (!flushedColumn.contains(columnName)) {
              ProjectManager.singleton.getInterProjectModel().flushJoinsInvolvingProjectColumn(project.id,columnName);
              flushedColumn.add(columnName);
            }
            row.setCell(c,new Cell(cell.value,reconMap.get(recon.id)));
          }
        }
      }
    }
  }
}
