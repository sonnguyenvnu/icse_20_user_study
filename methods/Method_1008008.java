@Override public void execute(IngestDocument document){
  fields.forEach(document::removeField);
}
