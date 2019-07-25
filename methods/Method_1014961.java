private void export(List<Record> recordList) throws IOException {
  ExcelWriter writer=ExcelUtil.getWriter();
  AdminFieldService adminFieldService=new AdminFieldService();
  List<Record> fieldList=adminFieldService.customFieldList("1");
  writer.addHeaderAlias("leads_name","????");
  writer.addHeaderAlias("next_time","??????");
  writer.addHeaderAlias("telephone","??");
  writer.addHeaderAlias("mobile","???");
  writer.addHeaderAlias("address","??");
  writer.addHeaderAlias("remark","??");
  writer.addHeaderAlias("create_user_name","???");
  writer.addHeaderAlias("owner_user_name","???");
  writer.addHeaderAlias("create_time","????");
  writer.addHeaderAlias("update_time","????");
  for (  Record field : fieldList) {
    writer.addHeaderAlias(field.getStr("name"),field.getStr("name"));
  }
  writer.merge(10 + fieldList.size(),"????");
  HttpServletResponse response=getResponse();
  List<Map<String,Object>> list=new ArrayList<>();
  for (  Record record : recordList) {
    list.add(record.remove("batch_id","is_transform","customer_id","leads_id","owner_user_id","create_user_id","followup","field_batch_id").getColumns());
  }
  writer.write(list,true);
  for (int i=0; i < fieldList.size() + 15; i++) {
    writer.setColumnWidth(i,20);
  }
  response.setContentType("application/vnd.ms-excel;charset=utf-8");
  response.setCharacterEncoding("UTF-8");
  response.setHeader("Content-Disposition","attachment;filename=leads.xls");
  ServletOutputStream out=response.getOutputStream();
  writer.flush(out);
  writer.close();
}
