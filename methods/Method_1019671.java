private void save(FieldsMetadata fieldsMetadata,Writer writer,OutputStream out,boolean indent) throws IOException, JSONException {
  Map<String,Object> bean=toMap(fieldsMetadata);
  JSONObject json=new JSONObject(bean);
  if (indent) {
    write(json.toString(1),writer,out);
  }
 else {
    write(json.toString(),writer,out);
  }
}
