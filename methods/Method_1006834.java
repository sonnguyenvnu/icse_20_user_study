public static void boot(DataSource ds_W,DataSource ds_R){
  if (instance == null) {
    instance=new RepositoryBooter();
    setDataSource(ds_W,ds_R);
  }
}
