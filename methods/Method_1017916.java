@Override public List<? extends NifiFeedProcessorStats> save(List<? extends NifiFeedProcessorStats> stats){
  if (stats != null && !stats.isEmpty()) {
    return statisticsRepository.save((List<JpaNifiFeedProcessorStats>)stats);
  }
  return stats;
}
