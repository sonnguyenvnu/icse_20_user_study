public void next(){
  Map<String,String> result=service.invokeAction(this,"AVTransport","Next",null);
  for (  String variable : result.keySet()) {
    this.onValueReceived(variable,result.get(variable),"AVTransport");
  }
}
