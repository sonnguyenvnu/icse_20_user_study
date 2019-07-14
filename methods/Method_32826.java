/** 
 * Get bootstrap zip url for this systems cpu architecture. 
 */
private static URL determineZipUrl() throws MalformedURLException {
  String archName=determineTermuxArchName();
  String url=Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ? "https://termux.org/bootstrap-" + archName + ".zip" : "https://termux.net/bootstrap/bootstrap-" + archName + ".zip";
  return new URL(url);
}
