public void dispatch(URL aURL,PropertyValue[] arg1){
  if (aURL.Protocol.compareTo("fr.opensagres.xdocreport.xdocreportdesigntool:") == 0) {
    if (aURL.Path.compareTo("XDocReport") == 0) {
      startNewThread(openSettings);
      return;
    }
 else     if (aURL.Path.compareTo("ListExisting") == 0) {
      if (Controller.INSTANCE.getXdocReportURL() != null) {
        startNewThread(listExisting);
      }
 else {
        startNewThread(openSettings);
      }
      return;
    }
  }
}
