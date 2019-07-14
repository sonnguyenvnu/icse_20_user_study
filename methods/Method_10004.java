/** 
 * Fills minified directory and file postfix for static JavaScript, CSS.
 * @param dataModel the specified data model
 */
public void fillMinified(final Map<String,Object> dataModel){
switch (Latkes.getRuntimeMode()) {
case DEVELOPMENT:
    dataModel.put(Common.MINI_POSTFIX,"");
  break;
case PRODUCTION:
dataModel.put(Common.MINI_POSTFIX,Common.MINI_POSTFIX_VALUE);
break;
default :
throw new AssertionError();
}
}
