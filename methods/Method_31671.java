/** 
 * Checks whether Flyway has to fallback to the old default table.
 * @param table The currently configured table.
 * @return The table to use.
 */
private Table determineTable(Table table){
  if (table.getName().equals("flyway_schema_history") && !table.exists()) {
    Table fallbackTable=table.getSchema().getTable("schema_version");
    if (fallbackTable.exists()) {
      LOG.warn("Could not find schema history table " + table + ", but found " + fallbackTable + " instead." + " You are seeing this message because Flyway changed its default for flyway.table in" + " version 5.0.0 to flyway_schema_history and you are still relying on the old default (schema_version)." + " Set flyway.table=schema_version in your configuration to fix this." + " This fallback mechanism will be removed in Flyway 6.0.0.");
      table=fallbackTable;
    }
  }
  return table;
}
