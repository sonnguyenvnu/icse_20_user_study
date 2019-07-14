public HistoryEntry queueProcess(Process process) throws Exception {
  if (process.isImmediate() && _processes.size() == 0) {
    _latestExceptions=null;
    return process.performImmediate();
  }
 else {
    _processes.add(process);
    update();
  }
  return null;
}
