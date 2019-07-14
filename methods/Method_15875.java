public String encodeColumn(Dialect dialect,String field){
  if (field.contains(".")) {
    String[] tmp=field.split("[.]");
    return tmp[0] + "." + dialect.getQuoteStart() + (dialect.columnToUpperCase() ? (tmp[1].toUpperCase()) : tmp[1]) + dialect.getQuoteEnd();
  }
 else {
    return dialect.getQuoteStart() + (dialect.columnToUpperCase() ? (field.toUpperCase()) : field) + dialect.getQuoteEnd();
  }
}
