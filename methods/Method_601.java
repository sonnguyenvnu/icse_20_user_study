public void configFromPropety(Properties properties){
{
    String property=properties.getProperty(DENY_PROPERTY);
    String[] items=splitItemsFormProperty(property);
    addItemsToDeny(items);
  }
{
    String property=properties.getProperty(AUTOTYPE_ACCEPT);
    String[] items=splitItemsFormProperty(property);
    addItemsToAccept(items);
  }
{
    String property=properties.getProperty(AUTOTYPE_SUPPORT_PROPERTY);
    if ("true".equals(property)) {
      this.autoTypeSupport=true;
    }
 else     if ("false".equals(property)) {
      this.autoTypeSupport=false;
    }
  }
}
