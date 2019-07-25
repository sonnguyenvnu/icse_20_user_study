@Override public void log(){
  if (log.isLoggable(Level.FINE)) {
    super.log();
    if (parsedDLNAHeaders != null && parsedDLNAHeaders.size() > 0) {
      log.fine("########################## PARSED DLNA HEADERS ##########################");
      for (      Map.Entry<DLNAHeader.Type,List<UpnpHeader>> entry : parsedDLNAHeaders.entrySet()) {
        log.log(Level.FINE,"=== TYPE: {0}",entry.getKey());
        for (        UpnpHeader upnpHeader : entry.getValue()) {
          log.log(Level.FINE,"HEADER: {0}",upnpHeader);
        }
      }
    }
    log.fine("####################################################################");
  }
}
