@Override public NifiFeedProcessorStats create(NifiFeedProcessorStats t){
  NifiFeedProcessorStats stats=statisticsRepository.save((JpaNifiFeedProcessorStats)t);
  return stats;
}
