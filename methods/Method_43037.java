public Date getTimestamp(){
  try {
    return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z",Locale.getDefault()).parse(timestamp);
  }
 catch (  IllegalArgumentException|ParseException e) {
    return new Date();
  }
}
