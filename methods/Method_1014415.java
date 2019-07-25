public void pause(){
  Map<String,String> result=service.invokeAction(this,"AVTransport","Pause",null);
  for (  String variable : result.keySet()) {
    this.onValueReceived(variable,result.get(variable),"AVTransport");
  }
}
