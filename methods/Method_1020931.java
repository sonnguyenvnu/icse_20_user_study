@Override public void load(final Settings settings){
  final List<Module> moduleList=new ArrayList<Module>();
  moduleList.add(new SettingsModule(settings));
  moduleList.add(new ThreadPoolModule());
  moduleList.add(new TransportModule());
  moduleList.add(new HttpModule());
  moduleList.add(new ScanModule());
  moduleList.add(new ControllerModule());
  boolean disableRedis=settings.getAsBoolean(ServiceFramwork.mode + ".datasources.redis.disable",true);
  if (!disableRedis) {
    moduleList.add(new CacheModule());
  }
  moduleList.add(new AppCacheModule());
  boolean disableMysql=settings.getAsBoolean(ServiceFramwork.mode + ".datasources.mysql.disable",false);
  if (!disableMysql) {
    moduleList.add(new AbstractModule(){
      @Override protected void configure(){
        bind(DBInfo.class).in(Singleton.class);
      }
    }
);
    moduleList.add(new AbstractModule(){
      @Override protected void configure(){
        String clzzName=settings.get("type_mapping","net.csdn.jpa.type.impl.MysqlType");
        final Class czz;
        try {
          czz=Class.forName(clzzName);
          bind(DBType.class).to(czz).in(Singleton.class);
        }
 catch (        ClassNotFoundException e) {
          e.printStackTrace();
        }
      }
    }
);
  }
  moduleList.addAll(ServiceFramwork.modules);
  ServiceFramwork.AllModules.addAll(moduleList);
}
