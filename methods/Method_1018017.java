/** 
 * Combine another statistics model
 */
public void combine(StandardStatisticsModel statisticsModel){
  for (  Integer k_columnIndex : statisticsModel.columnStatisticsMap.keySet()) {
    StandardColumnStatistics columnStatistics=columnStatisticsMap.get(k_columnIndex);
    StandardColumnStatistics v_columnStatistics=statisticsModel.columnStatisticsMap.get(k_columnIndex);
    if (columnStatistics != null) {
      columnStatistics.combine(v_columnStatistics);
    }
 else {
      columnStatisticsMap.put(k_columnIndex,v_columnStatistics);
    }
  }
}
