public void log(){
  if (log.isLoggable(Level.FINE)) {
    log.fine("############################ RAW HEADERS ###########################");
    for (    Entry<String,List<String>> entry : entrySet()) {
      log.fine("=== NAME : " + entry.getKey());
      for (      String v : entry.getValue()) {
        log.fine("VALUE: " + v);
      }
    }
    if (parsedHeaders != null && parsedHeaders.size() > 0) {
      log.fine("########################## PARSED HEADERS ##########################");
      for (      Map.Entry<UpnpHeader.Type,List<UpnpHeader>> entry : parsedHeaders.entrySet()) {
        log.fine("=== TYPE: " + entry.getKey());
        for (        UpnpHeader upnpHeader : entry.getValue()) {
          log.fine("HEADER: " + upnpHeader);
        }
      }
    }
    log.fine("####################################################################");
  }
}
