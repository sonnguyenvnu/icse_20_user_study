protected void seek(String unit,String target){
  if (unit != null && target != null) {
    Map<String,String> inputs=new HashMap<String,String>();
    try {
      inputs.put("InstanceID","0");
      inputs.put("Unit",unit);
      inputs.put("Target",target);
    }
 catch (    NumberFormatException ex) {
      logger.debug("Action Invalid Value Format Exception {}",ex.getMessage());
    }
    Map<String,String> result=service.invokeAction(this,"AVTransport","Seek",inputs);
    for (    String variable : result.keySet()) {
      this.onValueReceived(variable,result.get(variable),"AVTransport");
    }
  }
}
