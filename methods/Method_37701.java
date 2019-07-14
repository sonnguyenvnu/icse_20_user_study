@Override public String toString(){
  return super.toString() + "\nMax memory:              " + Format.humanReadableByteCount(getMaxMemory(),false) + "\nTotal memory:            " + Format.humanReadableByteCount(getTotalMemory(),false) + "\nFree memory:             " + Format.humanReadableByteCount(getFreeMemory(),false) + "\nAvailableMemory memory:  " + Format.humanReadableByteCount(getAvailableMemory(),false) + "\nProcess ID (PID):        " + getCurrentPID();
}
