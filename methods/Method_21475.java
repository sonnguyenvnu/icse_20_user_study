private String buildString(String separator,CSVResult result,String newLine){
  StringBuilder csv=new StringBuilder();
  csv.append(Joiner.on(separator).join(result.getHeaders()));
  csv.append(newLine);
  csv.append(Joiner.on(newLine).join(result.getLines()));
  return csv.toString();
}
