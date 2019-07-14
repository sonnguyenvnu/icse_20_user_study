public final String getInsertStatement(Table table){
  return "INSERT INTO " + table + " (" + quote("installed_rank") + ", " + quote("version") + ", " + quote("description") + ", " + quote("type") + ", " + quote("script") + ", " + quote("checksum") + ", " + quote("installed_by") + ", " + quote("execution_time") + ", " + quote("success") + ")" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
}
