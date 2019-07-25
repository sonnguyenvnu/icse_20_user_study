public void play(){
  Map<String,String> inputs=new HashMap<String,String>();
  inputs.put("Speed","1");
  Map<String,String> result=service.invokeAction(this,"AVTransport","Play",inputs);
  for (  String variable : result.keySet()) {
    this.onValueReceived(variable,result.get(variable),"AVTransport");
  }
}
