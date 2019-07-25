public void stop(){
  Map<String,String> result=service.invokeAction(this,"AVTransport","Stop",null);
  for (  String variable : result.keySet()) {
    this.onValueReceived(variable,result.get(variable),"AVTransport");
  }
}
