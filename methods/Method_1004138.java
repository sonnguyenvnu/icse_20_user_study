/** 
 * Renders a table for the given icon
 * @param parent parent element in which the table is created
 * @param items items that will make the table rows
 * @param total the summary of all coverage data items in the table static resources that might be referenced
 * @param resources static resources that might be referenced
 * @param base base folder of the table
 * @throws IOException in case of IO problems with the element output
 */
public void render(final HTMLElement parent,final List<? extends ITableItem> items,final ICoverageNode total,final Resources resources,final ReportOutputFolder base) throws IOException {
  final List<? extends ITableItem> sortedItems=sort(items);
  final HTMLElement table=parent.table(Styles.COVERAGETABLE);
  table.attr("id","coveragetable");
  header(table,sortedItems,total);
  footer(table,total,resources,base);
  body(table,sortedItems,resources,base);
}
