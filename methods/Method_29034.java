/** 
 * ????
 * @return
 */
public static List<String> getDependencyRedis(){
  List<String> dependencyRedis=new ArrayList<String>();
  String redisGoodVersion=getGoodVersion();
  dependencyRedis.add("&lt;dependency&gt;                                                     ");
  dependencyRedis.add("&nbsp;&nbsp;&nbsp;&nbsp;&lt;groupId&gt;com.sohu.tv&lt;/groupId&gt;                                   ");
  dependencyRedis.add("&nbsp;&nbsp;&nbsp;&nbsp;&lt;artifactId&gt;cachecloud-open-client-redis&lt;/artifactId&gt;                       ");
  dependencyRedis.add("&nbsp;&nbsp;&nbsp;&nbsp;&lt;version&gt;" + redisGoodVersion + "&lt;/version&gt;                                           ");
  dependencyRedis.add("&lt;/dependency&gt;                                                    ");
  dependencyRedis.add("&lt;repositories&gt;                                                   ");
  dependencyRedis.add("&nbsp;&nbsp;&nbsp;&nbsp;&lt;repository&gt;                                                     ");
  dependencyRedis.add("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;id&gt;sohu.nexus&lt;/id&gt;                                              ");
  dependencyRedis.add("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;url&gt;" + ConstUtils.MAVEN_WAREHOUSE + "&lt;/url&gt;");
  dependencyRedis.add("&nbsp;&nbsp;&nbsp;&nbsp;&lt;/repository&gt;                                                    ");
  dependencyRedis.add("&lt;/repositories&gt;                                                  ");
  return dependencyRedis;
}
