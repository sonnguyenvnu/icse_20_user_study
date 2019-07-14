public String getSelectStatement(Table table){
  return "SELECT " + quote("installed_rank") + "," + quote("version") + "," + quote("description") + "," + quote("type") + "," + quote("script") + "," + quote("checksum") + "," + quote("installed_on") + "," + quote("installed_by") + "," + quote("execution_time") + "," + quote("success") + " FROM " + table + " WHERE " + quote("installed_rank") + " > ?" + " ORDER BY " + quote("installed_rank");
}
