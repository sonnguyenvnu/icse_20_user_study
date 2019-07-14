@Override public String getRawCreateScript(Table table,boolean baseline){
  String tablespace=configuration.getTablespace() == null ? "" : " TABLESPACE \"" + configuration.getTablespace() + "\"";
  String baselineMarker="";
  if (baseline) {
    if (!pxcStrict) {
      baselineMarker=" AS SELECT" + "     1 as \"installed_rank\"," + "     '" + configuration.getBaselineVersion() + "' as \"version\"," + "     '" + configuration.getBaselineDescription() + "' as \"description\"," + "     '" + MigrationType.BASELINE + "' as \"type\"," + "     '" + configuration.getBaselineDescription() + "' as \"script\"," + "     NULL as \"checksum\"," + "     '" + getInstalledBy() + "' as \"installed_by\"," + "     CURRENT_TIMESTAMP as \"installed_on\"," + "     0 as \"execution_time\"," + "     TRUE as \"success\"\n";
    }
 else {
      baselineMarker=";\n" + getBaselineStatement(table);
    }
  }
  return "CREATE TABLE " + table + " (\n" + "    `installed_rank` INT NOT NULL,\n" + "    `version` VARCHAR(50),\n" + "    `description` VARCHAR(200) NOT NULL,\n" + "    `type` VARCHAR(20) NOT NULL,\n" + "    `script` VARCHAR(1000) NOT NULL,\n" + "    `checksum` INT,\n" + "    `installed_by` VARCHAR(100) NOT NULL,\n" + "    `installed_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" + "    `execution_time` INT NOT NULL,\n" + "    `success` BOOL NOT NULL,\n" + "    CONSTRAINT `" + table.getName() + "_pk` PRIMARY KEY (`installed_rank`)\n" + ")" + tablespace + " ENGINE=InnoDB" + baselineMarker + ";\n" + "CREATE INDEX `" + table.getName() + "_s_idx` ON " + table + " (`success`);";
}
