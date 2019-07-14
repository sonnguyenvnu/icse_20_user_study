@Bean(name="pollingParam") public PollingParam pollingParam(){
  PollingParam pollingParam=new PollingParam();
  Map<Integer,Integer> notifyParams=new HashMap<>();
  notifyParams.put(1,2);
  notifyParams.put(2,3);
  notifyParams.put(3,5);
  notifyParams.put(4,10);
  notifyParams.put(5,20);
  notifyParams.put(6,30);
  pollingParam.setNotifyParams(notifyParams);
  pollingParam.setSuccessValue("SUCCESS");
  return pollingParam;
}
