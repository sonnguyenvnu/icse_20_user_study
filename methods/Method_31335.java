@Override public String getRawCreateScript(Table table,boolean baseline){
  return "CREATE TABLE " + table + " (\n" + "    \"installed_rank\" INT NOT NULL,\n" + "    \"version\" VARCHAR(50),\n" + "    \"description\" VARCHAR(200) NOT NULL,\n" + "    \"type\" VARCHAR(20) NOT NULL,\n" + "    \"script\" VARCHAR(1000) NOT NULL,\n" + "    \"checksum\" INT,\n" + "    \"installed_by\" VARCHAR(100) NOT NULL,\n" + "    \"installed_on\" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,\n" + "    \"execution_time\" INT NOT NULL,\n" + "    \"success\" BIT NOT NULL\n" + ");\n" + (baseline ? getBaselineStatement(table) + ";\n" : "") + "ALTER TABLE " + table + " ADD CONSTRAINT \"" + table.getName() + "_pk\" PRIMARY KEY (\"installed_rank\");\n" + "CREATE INDEX \"" + table.getSchema().getName() + "\".\"" + table.getName() + "_s_idx\" ON " + table + " (\"success\");";
}