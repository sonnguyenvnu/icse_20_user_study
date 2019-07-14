/** 
 * Create the name under which this scheduler should be registered in JMX. <p> The name is composed as: quartz:type=QuartzScheduler,name=<i>[schedName]</i>,instance=<i>[schedInstId]</i> </p>
 */
public static String generateJMXObjectName(String schedName,String schedInstId){
  return "quartz:type=QuartzScheduler" + ",name=" + schedName.replaceAll(":|=|\n",".") + ",instance=" + schedInstId;
}
