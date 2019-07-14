private static Date fromRfc3339DateStringQuietly(String timestamp){
  try {
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    return simpleDateFormat.parse(timestamp);
  }
 catch (  ParseException e) {
    return null;
  }
}
