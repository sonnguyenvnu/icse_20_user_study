private void export(List<Record> recordList) throws IOException {
  ExcelWriter writer=ExcelUtil.getWriter();
  AdminFieldService adminFieldService=new AdminFieldService();
  List<Record> fieldList=adminFieldService.customFieldList("2");
  writer.addHeaderAlias("customer_name","????");
  writer.addHeaderAlias("telephone","??");
  writer.addHeaderAlias("mobile","??");
  writer.addHeaderAlias("website","??");
  writer.addHeaderAlias("next_time","??????");
  writer.addHeaderAlias("deal_status","????");
  writer.addHeaderAlias("create_user_name","???");
  writer.addHeaderAlias("owner_user_name","???");
  writer.addHeaderAlias("address","???");
  writer.addHeaderAlias("location","????");
  writer.addHeaderAlias("detail_address","????");
  writer.addHeaderAlias("lng","??????");
  writer.addHeaderAlias("lat","??????");
  writer.addHeaderAlias("create_time","????");
  writer.addHeaderAlias("update_time","????");
  writer.addHeaderAlias("remark","??");
  for (  Record field : fieldList) {
    writer.addHeaderAlias(field.getStr("name"),field.getStr("name"));
  }
  writer.merge(fieldList.size() + 15,"????");
  HttpServletResponse response=getResponse();
  List<Map<String,Object>> list=new ArrayList<>();
  for (  Record record : recordList) {
    list.add(record.remove("batch_id","create_user_id","customer_id","is_lock","owner_user_id","ro_user_id","rw_user_id","followup","field_batch_id").getColumns());
  }
  writer.write(list,true);
  for (int i=0; i < fieldList.size() + 16; i++) {
    writer.setColumnWidth(i,20);
  }
  response.setContentType("application/vnd.ms-excel;charset=utf-8");
  response.setCharacterEncoding("UTF-8");
  response.setHeader("Content-Disposition","attachment;filename=customer.xls");
  ServletOutputStream out=response.getOutputStream();
  writer.flush(out);
  writer.close();
}
