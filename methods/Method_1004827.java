public void match(String database,String table,FilterResult match){
  if (appliesTo(database,table))   match.include=(this.type == FilterPatternType.INCLUDE);
}
