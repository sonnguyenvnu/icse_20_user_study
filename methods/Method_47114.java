/** 
 * Updates the status spans
 */
private void updateSpans(){
  String ftpAddress=getFTPAddressString();
  if (ftpAddress == null) {
    ftpAddress="";
    Toast.makeText(getContext(),getResources().getString(R.string.local_inet_addr_error),Toast.LENGTH_SHORT).show();
  }
  String statusHead=getResources().getString(R.string.ftp_status_title) + ": ";
  spannedStatusConnected=Html.fromHtml(statusHead + "<b>&nbsp;&nbsp;" + "<font color='" + accentColor + "'>" + getResources().getString(R.string.ftp_status_running) + "</font></b>");
  spannedStatusUrl=Html.fromHtml("URL:&nbsp;" + ftpAddress);
  spannedStatusNoConnection=Html.fromHtml(statusHead + "<b>&nbsp;&nbsp;&nbsp;&nbsp;" + "<font color='" + Utils.getColor(getContext(),android.R.color.holo_red_light) + "'>" + getResources().getString(R.string.ftp_status_no_connection) + "</font></b>");
  spannedStatusNotRunning=Html.fromHtml(statusHead + "<b>&nbsp;&nbsp;&nbsp;&nbsp;" + getResources().getString(R.string.ftp_status_not_running) + "</b>");
  spannedStatusSecure=Html.fromHtml(statusHead + "<b>&nbsp;&nbsp;&nbsp;&nbsp;" + "<font color='" + Utils.getColor(getContext(),android.R.color.holo_green_light) + "'>" + getResources().getString(R.string.ftp_status_secure_connection) + "</font></b>");
  spannedStatusUrl=Html.fromHtml("URL:&nbsp;" + ftpAddress);
}
