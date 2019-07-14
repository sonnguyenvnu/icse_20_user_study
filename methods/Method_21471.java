public static RestExecutor createExecutor(String format){
  if (format == null || format.equals("")) {
    return new ElasticDefaultRestExecutor();
  }
  if (format.equalsIgnoreCase("csv")) {
    return new CSVResultRestExecutor();
  }
  return null;
}
