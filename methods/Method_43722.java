/** 
 * @return the report id 
 */
public String requestNewReport(CoinbasePro.CoinbaseProReportRequest reportRequest) throws IOException {
  Map response=coinbasePro.createReport(apiKey,digest,nonceFactory,passphrase,reportRequest);
  return response.get("id").toString();
}
