private static void dropAllTables(StandardDatabase db,boolean ifExists,@NonNull Class<? extends AbstractDao<?,?>>... daoClasses){
  reflectMethod(db,"dropTable",ifExists,daoClasses);
}
