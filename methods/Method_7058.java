private static void parseExpiryDate(String expiryDate,Result result){
  try {
    if ("<<<<<<".equals(expiryDate)) {
      result.doesNotExpire=true;
    }
 else {
      result.expiryYear=2000 + Integer.parseInt(expiryDate.substring(0,2));
      result.expiryMonth=Integer.parseInt(expiryDate.substring(2,4));
      result.expiryDay=Integer.parseInt(expiryDate.substring(4));
    }
  }
 catch (  NumberFormatException ignore) {
  }
}
