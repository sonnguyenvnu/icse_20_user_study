@Override public String execute(){
  if (event == null) {
    return parsingResult;
  }
 else   if (DemoCommandsPluggable.eventAdmin != null) {
    DemoCommandsPluggable.eventAdmin.postEvent(event);
    return SUCCESS;
  }
 else {
    return FAIL;
  }
}
