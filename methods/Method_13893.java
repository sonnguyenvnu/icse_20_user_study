private void listFusionTables(Fusiontables service,JsonGenerator writer) throws IOException {
  Fusiontables.Table.List listTables=service.table().list();
  TableList tablelist=listTables.execute();
  if (tablelist == null || tablelist.getItems() == null)   return;
  for (  Table table : tablelist.getItems()) {
    String id=table.getTableId();
    String name=table.getName();
    String link="https://www.google.com/fusiontables/DataSource?docid=" + id;
    writer.writeStartObject();
    writer.writeStringField("docId",id);
    writer.writeStringField("docLink",link);
    writer.writeStringField("docSelfLink",link);
    writer.writeStringField("title",name);
    writer.writeStringField("type","table");
    writer.writeEndObject();
  }
}
