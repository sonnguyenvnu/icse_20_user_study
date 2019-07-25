public static Map<String,String> properties(Settings mysqlSetting){
  Map<String,String> properties=new HashMap<String,String>();
  properties.put("hibernate.connection.provider_class",mysqlSetting.get("provider_class","net.csdn.hibernate.support.DruidConnectionProvider"));
  properties.put("show_sql",mysqlSetting.get("show_sql","true"));
  properties.put("driver_class",mysqlSetting.get("driver","com.mysql.jdbc.Driver"));
  properties.put("dialect","org.hibernate.dialect.MySQLDialect");
  properties.put("format_sql",mysqlSetting.get("format_sql","false"));
  properties.put("url","jdbc:mysql://" + mysqlSetting.get("host") + ":" + mysqlSetting.get("port") + "/" + mysqlSetting.get("database") + "?useUnicode=true&characterEncoding=utf8");
  properties.put("username",mysqlSetting.get("username"));
  properties.put("password",mysqlSetting.get("password"));
  properties.put("maxActive",mysqlSetting.get("maxActive","50"));
  properties.put("minIdle",mysqlSetting.get("minIdle","3"));
  properties.put("initialSize",mysqlSetting.get("initialSize","30"));
  properties.put("maxWait",mysqlSetting.get("maxWait","20"));
  properties.put("testOnBorrow",mysqlSetting.get("testOnBorrow","false"));
  properties.put("validationQuery",mysqlSetting.get("validationQuery","SELECT 1"));
  properties.put("validationQueryTimeout",mysqlSetting.get("validationQueryTimeout","60000"));
  properties.put("removeAbandoned",mysqlSetting.get("removeAbandoned","false"));
  properties.put("removeAbandonedTimeout",mysqlSetting.get("removeAbandonedTimeout","1800"));
  properties.put("logAbandoned",mysqlSetting.get("logAbandoned","false"));
  properties.put("init",mysqlSetting.get("init","true"));
  properties.put("testWhileIdle",mysqlSetting.get("testWhileIdle","true"));
  properties.put("connectionProperties","druid.stat.logSlowSql=" + mysqlSetting.get("logSlowSql","true") + ";druid.stat.slowSqlMillis=" + mysqlSetting.get("slowSqlMillis","500"));
  properties.put("filters","log4j");
  return properties;
}
