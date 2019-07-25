private boolean empty(Stats stats){
  if (stats == null) {
    return true;
  }
  return stats.cacheSize == 0 && stats.ramBytesUsed == 0;
}
