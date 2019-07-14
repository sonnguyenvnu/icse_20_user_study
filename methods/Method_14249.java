@Override public void export(Project project,Properties options,Engine engine,Writer writer) throws IOException {
  String limitString=options.getProperty("limit");
  int limit=limitString != null ? Integer.parseInt(limitString) : -1;
  String sortingJson=options.getProperty("sorting");
  String templateString=options.getProperty("template");
  String prefixString=options.getProperty("prefix");
  String suffixString=options.getProperty("suffix");
  String separatorString=options.getProperty("separator");
  Template template;
  try {
    template=Parser.parse(templateString);
  }
 catch (  ParsingException e) {
    throw new IOException("Missing or bad template",e);
  }
  template.setPrefix(prefixString);
  template.setSuffix(suffixString);
  template.setSeparator(separatorString);
  if (!"true".equals(options.getProperty("preview"))) {
    TemplateConfig config=new TemplateConfig(templateString,prefixString,suffixString,separatorString);
    project.getMetadata().getPreferenceStore().put("exporters.templating.template",ParsingUtilities.defaultWriter.writeValueAsString(config));
  }
  if (engine.getMode() == Mode.RowBased) {
    FilteredRows filteredRows=engine.getAllFilteredRows();
    RowVisitor visitor=template.getRowVisitor(writer,limit);
    if (sortingJson != null) {
      SortingConfig sorting=SortingConfig.reconstruct(sortingJson);
      SortingRowVisitor srv=new SortingRowVisitor(visitor);
      srv.initializeFromConfig(project,sorting);
      if (srv.hasCriteria()) {
        visitor=srv;
      }
    }
    filteredRows.accept(project,visitor);
  }
 else {
    FilteredRecords filteredRecords=engine.getFilteredRecords();
    RecordVisitor visitor=template.getRecordVisitor(writer,limit);
    if (sortingJson != null) {
      SortingConfig sorting=SortingConfig.reconstruct(sortingJson);
      SortingRecordVisitor srv=new SortingRecordVisitor(visitor);
      srv.initializeFromConfig(project,sorting);
      if (srv.hasCriteria()) {
        visitor=srv;
      }
    }
    filteredRecords.accept(project,visitor);
  }
}
