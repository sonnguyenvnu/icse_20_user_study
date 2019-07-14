@Bean(name="notifyParam") public NotifyParam notifyParam(){
  NotifyParam notifyParam=new NotifyParam();
  Map<Integer,Integer> notifyParams=new HashMap<>();
  notifyParams.put(1,0);
  notifyParams.put(2,1);
  notifyParams.put(3,2);
  notifyParams.put(4,5);
  notifyParams.put(5,15);
  notifyParam.setNotifyParams(notifyParams);
  notifyParam.setSuccessValue("success");
  return notifyParam;
}
