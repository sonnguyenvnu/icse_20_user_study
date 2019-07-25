public void previous(){
  Map<String,String> result=service.invokeAction(this,"AVTransport","Previous",null);
  for (  String variable : result.keySet()) {
    this.onValueReceived(variable,result.get(variable),"AVTransport");
  }
}
