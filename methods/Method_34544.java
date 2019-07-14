public static String convertToString(HystrixConfiguration config) throws IOException {
  StringWriter jsonString=new StringWriter();
  JsonGenerator json=jsonFactory.createGenerator(jsonString);
  json.writeStartObject();
  json.writeStringField("type","HystrixConfig");
  json.writeObjectFieldStart("commands");
  for (  Map.Entry<HystrixCommandKey,HystrixCommandConfiguration> entry : config.getCommandConfig().entrySet()) {
    final HystrixCommandKey key=entry.getKey();
    final HystrixCommandConfiguration commandConfig=entry.getValue();
    writeCommandConfigJson(json,key,commandConfig);
  }
  json.writeEndObject();
  json.writeObjectFieldStart("threadpools");
  for (  Map.Entry<HystrixThreadPoolKey,HystrixThreadPoolConfiguration> entry : config.getThreadPoolConfig().entrySet()) {
    final HystrixThreadPoolKey threadPoolKey=entry.getKey();
    final HystrixThreadPoolConfiguration threadPoolConfig=entry.getValue();
    writeThreadPoolConfigJson(json,threadPoolKey,threadPoolConfig);
  }
  json.writeEndObject();
  json.writeObjectFieldStart("collapsers");
  for (  Map.Entry<HystrixCollapserKey,HystrixCollapserConfiguration> entry : config.getCollapserConfig().entrySet()) {
    final HystrixCollapserKey collapserKey=entry.getKey();
    final HystrixCollapserConfiguration collapserConfig=entry.getValue();
    writeCollapserConfigJson(json,collapserKey,collapserConfig);
  }
  json.writeEndObject();
  json.writeEndObject();
  json.close();
  return jsonString.getBuffer().toString();
}
