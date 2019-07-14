public boolean queueProcess(HistoryProcess process) throws Exception {
  if (process.isImmediate() && _processes.size() == 0) {
    _latestExceptions=null;
    return process.performImmediate() != null;
  }
 else {
    _processes.add(process);
    update();
  }
  return false;
}
