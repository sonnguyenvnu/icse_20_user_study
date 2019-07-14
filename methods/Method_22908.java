/** 
 * Handler for File &rarr; Page Setup.
 */
public void handlePageSetup(){
  if (printerJob == null) {
    printerJob=PrinterJob.getPrinterJob();
  }
  if (pageFormat == null) {
    pageFormat=printerJob.defaultPage();
  }
  pageFormat=printerJob.pageDialog(pageFormat);
}
