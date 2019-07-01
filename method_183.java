protected String _XXXXX_(int year,int month,int day){
  StringBuilder sb=new StringBuilder();
  sb.append(basePath);
  sb.append(String.format(YEAR_MONTH_DAY_URL_FORMAT,year,month,day));
  return sb.toString();
}