public String exec(){
  String callbackName=this.request.getParameter("callback");
  if (callbackName != null) {
    if (!validCallbackName(callbackName)) {
      return new BaseState(false,AppInfo.ILLEGAL).toJSONString();
    }
    return callbackName + "(" + this.invoke() + ");";
  }
 else {
    return this.invoke();
  }
}
