/** 
 * Corresponds to <code> GET /products/&lt;product-id&gt;/candles?granularity=[granularity]&start=[UTC time of start]&end=[UTC time of end] </code>
 * @param productID
 * @param granularitySeconds Desired timeslice in seconds
 * @param start Start time
 * @param end End time
 * @return
 * @throws IOException
 */
public AbucoinsHistoricRate[] getAbucoinsHistoricRates(String productID,long granularitySeconds,Date start,Date end) throws IOException {
  if (start == null || end == null)   throw new IllegalArgumentException("Must provide begin and end dates");
  String granularity=String.valueOf(granularitySeconds);
  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
  dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
  String startDate=dateFormat.format(start);
  String endDate=dateFormat.format(end);
  AbucoinsHistoricRates rates=abucoins.getHistoricRates(productID,granularity,startDate,endDate);
  if (rates.getMessage() != null)   throw new ExchangeException(rates.getMessage());
  return rates.getHistoricRates();
}
