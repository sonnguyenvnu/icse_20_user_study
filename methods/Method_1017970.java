@Override public void configure(Map<String,String> params){
  jmsUrl=params.get(JMS_URL_CONFIG);
  if (StringUtils.isBlank(jmsUrl)) {
    jmsUrl="tcp://localhost:61616";
  }
}
