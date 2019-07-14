private static void createAllTables(StandardDatabase db,boolean ifNotExists,@NonNull Class<? extends AbstractDao<?,?>>... daoClasses){
  reflectMethod(db,"createTable",ifNotExists,daoClasses);
}
