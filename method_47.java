private JobConfig _XXXXX_(InputStream is) throws Exception {
  JobConfig jobConfig=new JobConfig();
  try (BufferedReader reader=new BufferedReader(new InputStreamReader(is))){
    String line;
    boolean stop=false;
    while ((line=reader.readLine()) != null && !stop) {
      try {
        JSONParser parser=new JSONParser();
        JSONObject eventObj=(JSONObject)parser.parse(line);
        if (eventObj != null) {
          String eventType=(String)eventObj.get("Event");
          LOG.info("Event type: " + eventType);
          if (eventType.equalsIgnoreCase(SparkEventType.SparkListenerEnvironmentUpdate.toString())) {
            stop=true;
            JSONObject sparkProps=(JSONObject)eventObj.get("Spark Properties");
            for (            Object key : sparkProps.keySet()) {
              jobConfig.put((String)key,(String)sparkProps.get(key));
            }
          }
        }
      }
 catch (      Exception e) {
        LOG.error(String.format("Fail to parse %s.",line),e);
      }
    }
    return jobConfig;
  }
 }