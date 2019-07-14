private static void generateNewTablesIfNotExists(StandardDatabase db,Class<? extends AbstractDao<?,?>>... daoClasses){
  reflectMethod(db,"createTable",true,daoClasses);
}
