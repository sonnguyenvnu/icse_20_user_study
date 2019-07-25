/** 
 * Construct a ColumnPrinter using the entries 
 * @param entries
 * @return
 */
private ColumnPrinter build(Map<String,Entry> entries){
  ColumnPrinter printer=new ColumnPrinter();
  printer.addColumn("PROPERTY");
  printer.addColumn("FIELD");
  printer.addColumn("DEFAULT");
  printer.addColumn("VALUE");
  printer.addColumn("DESCRIPTION");
  Map<String,Entry> sortedEntries=Maps.newTreeMap();
  sortedEntries.putAll(entries);
  for (  Entry entry : sortedEntries.values()) {
    printer.addValue(0,entry.configurationName);
    printer.addValue(1,entry.field.getDeclaringClass().getName() + "#" + entry.field.getName());
    printer.addValue(2,entry.defaultValue);
    printer.addValue(3,entry.has ? entry.value : "");
    printer.addValue(4,entry.documentation);
  }
  return printer;
}
