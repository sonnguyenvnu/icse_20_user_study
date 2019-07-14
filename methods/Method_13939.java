protected void translateNameDescr(String qid,Set<MonolingualTextValue> values,String prefix,ItemIdValue id,Writer writer) throws IOException {
  for (  MonolingualTextValue value : values) {
    writer.write(qid + "\t");
    writer.write(prefix);
    writer.write(value.getLanguageCode());
    writer.write("\t\"");
    writer.write(value.getText());
    writer.write("\"\n");
  }
}
