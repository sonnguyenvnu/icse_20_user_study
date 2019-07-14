/** 
 * Cancel any pending crash reports.
 */
public void cancelReports(){
  new Thread(() -> new BulkReportDeleter(context).deleteReports(false,0)).start();
}
