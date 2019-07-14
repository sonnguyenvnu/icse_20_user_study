public static DbOomQuery query(final DbSqlGenerator sqlgen){
  return new DbOomQuery(DbOom.get(),sqlgen);
}
