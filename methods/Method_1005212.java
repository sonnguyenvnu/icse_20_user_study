public String invoke(String templatePath,DataGridTag dataGridTag) throws Exception {
  FreemarkerHelper free=new FreemarkerHelper();
  Map<String,Object> data=getData(dataGridTag);
  String content=free.parseTemplate(templatePath,data);
  return content;
}
