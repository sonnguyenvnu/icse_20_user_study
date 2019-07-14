public Date getTimestamp(){
  try {
    return TRANSACTION_DATE_FORMAT.parse(transactionDate);
  }
 catch (  ParseException e) {
    return null;
  }
}
