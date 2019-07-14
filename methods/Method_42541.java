/** 
 * ?????
 * @param billDate ???
 * @param dir ??????
 */
public File fileDown(Date fileDate,String dir) throws Exception {
  LOG.info("======??????????");
  String bill_begin_date=billDateSDF.format(fileDate);
  String bill_end_date=billDateSDF.format(DateUtils.addDay(fileDate,1));
  gmt_start_time=bill_begin_date + " 00:00:00";
  gmt_end_time=bill_end_date + " 00:00:00";
  HttpResponse response=null;
  Map<String,String> sParaTemp=new HashMap<String,String>();
  sParaTemp.put("service","account.page.query");
  sParaTemp.put("partner",partner);
  sParaTemp.put("_input_charset",charset);
  sParaTemp.put("page_no",pageNo);
  sParaTemp.put("gmt_start_time",gmt_start_time);
  sParaTemp.put("gmt_end_time",gmt_end_time);
  response=this.buildRequest(sParaTemp);
  if (response == null) {
    return null;
  }
  String stringResult=response.getStringResult();
  File file=this.createFile(bill_begin_date,stringResult,dir);
  return file;
}
