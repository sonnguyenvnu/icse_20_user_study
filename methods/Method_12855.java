private void setTotalRowCount(String name,Attributes attributes){
  if (DIMENSION.equals(name)) {
    String d=attributes.getValue(DIMENSION_REF);
    String totalStr=d.substring(d.indexOf(":") + 1,d.length());
    String c=totalStr.toUpperCase().replaceAll("[A-Z]","");
    analysisContext.setTotalCount(Integer.parseInt(c));
  }
}
