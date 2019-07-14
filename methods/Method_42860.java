/** 
 * Corresponds to <code>GET /products/ticker</code>
 * @return
 * @throws IOException
 */
public AbucoinsFullTicker[] getAbucoinsTickers() throws IOException {
  AbucoinsFullTicker[] abucoinsTickers=abucoins.getTicker();
  return abucoinsTickers;
}
