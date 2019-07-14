public DbSqlBuilder setAll(final String tableRef,final Object values){
  return addChunk(new UpdateSetChunk(dbOom,tableRef,values,SqlChunk.COLS_ALL));
}
