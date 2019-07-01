@Override public void _XXXXX_(String type,Map<String,HealthCheck.Result> results){
  if (results.size() == 0) {
    return;
  }
  Properties properties=parseMailClientConfig();
  if (properties == null) {
    return;
  }
  int count=0;
  boolean success=false;
  while (count++ < MAX_RETRY_COUNT && !success) {
    LOG.info("Sending email, tried: " + count + ", max: "+ MAX_RETRY_COUNT);
    try {
      String recipients=config.getString(CONF_MAIL_RECIPIENTS);
      if (recipients == null || recipients.equals("")) {
        LOG.error("Recipients is null, skip sending emails ");
        return;
      }
      final VelocityContext context=new VelocityContext();
      Map<String,String> appMsgs=new HashMap<>();
      int unhealthyCount=0;
      int healthyCount=0;
      for (      String appId : results.keySet()) {
        appMsgs.put(appId,results.get(appId).getMessage());
        if (!results.get(appId).isHealthy()) {
          unhealthyCount++;
        }
 else {
          healthyCount++;
        }
      }
      Map<String,Object> unHealthyContext=new HashMap<>();
      unHealthyContext.put("appMsgs",appMsgs);
      unHealthyContext.put("appMgmtUrl","http://" + config.getString(SERVICE_HOST) + ":"+ config.getInt(SERVICE_PORT)+ "/#/integration/site");
      unHealthyContext.put("healthCheckUrl","http://" + config.getString(SERVICE_HOST) + ":"+ HEALTH_CHECK_PORT+ "/healthcheck");
      context.put(UNHEALTHY_CONTEXT,unHealthyContext);
      String subject="";
      if (healthyCount > 0) {
        subject+=healthyCount + " healthy app(s)";
      }
      if (unhealthyCount > 0) {
        if (!subject.isEmpty()) {
          subject+=", ";
        }
        subject+=unhealthyCount + " unhealthy app(s)";
      }
      subject=config.getString(CONF_MAIL_SUBJECT) + ": " + subject;
      EagleMailClient client=new EagleMailClient(properties);
      String hostname=InetAddress.getLocalHost().getHostName();
      if (!hostname.endsWith(".com")) {
        hostname+=".com";
      }
      success=client.send(System.getProperty("user.name") + "@" + hostname,recipients,config.hasPath(CONF_MAIL_CC) ? config.getString(CONF_MAIL_CC) : null,type + subject,config.getString(CONF_MAIL_TEMPLATE),context,null);
      LOG.info("Success of sending email: " + success);
      if (!success && count < MAX_RETRY_COUNT) {
        LOG.info("Sleep for a while before retrying");
        Thread.sleep(10 * 1000);
      }
    }
 catch (    Exception e) {
      LOG.warn("Sending mail exception",e);
    }
  }
  if (success) {
    LOG.info("Successfully send unhealthy email");
  }
 else {
    LOG.warn("Fail sending unhealthy email after tries {} times",MAX_RETRY_COUNT);
  }
}