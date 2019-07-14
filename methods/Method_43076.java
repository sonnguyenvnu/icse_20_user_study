/** 
 * This complete messy it's because the 'oReturn' JSON field can be either an Object, or a String or an Array of Objects. <p>Examples: oReturn as a String (representing an exception or error): <pre> oReturn : 'NO_CONTENT_FOUND' </pre> oReturn as an Object (representing a single order): <pre> oReturn : { id: 1, asset: 'BTC', price : 15000.00 } </pre> oReturn as an Array of Objects (representing multiple-orders): <pre> oReturn : [ { id: 1, asset: 'BTC', price : 15000.00 }, { id: 2, asset: 'BTC', price : 15000.00 } ] </pre> I haven't figured out a better way to do this, like Jackson built-in annotations or something. <p>Please see  {@code BitcointoyouOrderResponseTest}
 * @param oReturn the 'oReturn' JSON field content
 */
private void setOReturn(Object oReturn){
  if (oReturn != null) {
    if (oReturn instanceof String) {
      this.oReturnAsString=(String)oReturn;
    }
 else     if (oReturn instanceof List) {
      this.oReturn=new ArrayList<>();
      for (      Object obj : (List)oReturn) {
        if (obj instanceof LinkedHashMap) {
          addNewBitcointoyouOrderInfo((Map<String,String>)obj);
        }
 else         if (obj instanceof BitcointoyouOrderInfo) {
          this.oReturn.add((BitcointoyouOrderInfo)obj);
        }
      }
    }
 else     if (oReturn instanceof Map) {
      this.oReturn=new ArrayList<>();
      addNewBitcointoyouOrderInfo((Map<String,String>)oReturn);
    }
  }
}
