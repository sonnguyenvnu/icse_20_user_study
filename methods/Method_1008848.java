/** 
 * Custom build method, get's used with a FldSimpleModel in those cases where the hyperlink is defined within a Field
 */
public void build(AbstractWmlConversionContext conversionContext,FldSimpleModel fldSimpleModel,Node content) throws TransformerException {
  int idx=0;
  List<String> parameters=fldSimpleModel.getFldParameters();
  String parameter=null;
  boolean isSwitch=false;
  char switchChar='\0';
  String switchParameter=null;
  this.content=content;
  while (idx < parameters.size()) {
    parameter=parameters.get(idx);
    if ((parameter != null) && (parameter.length() > 0)) {
      isSwitch=((parameter.charAt(0) == '\\') && (parameter.length() == 2));
      if (isSwitch) {
        switchChar=Character.toLowerCase(parameter.charAt(1));
switch (switchChar) {
case 'l':
          switchParameter=FormattingSwitchHelper.getSwitchValue(idx + 1,parameters);
        if (switchParameter != null) {
          setTarget(switchParameter);
          idx++;
        }
      break;
case 't':
    switchParameter=FormattingSwitchHelper.getSwitchValue(idx + 1,parameters);
  if (switchParameter != null) {
    setTgtFrame(switchParameter);
    idx++;
  }
break;
case 'n':
setTgtFrame("_blank");
break;
case 'o':
switchParameter=FormattingSwitchHelper.getSwitchValue(idx + 1,parameters);
if (switchParameter != null) {
setTooltip(switchParameter);
idx++;
}
break;
case 'm':
break;
}
}
 else {
if (idx == 0) {
setTarget(FormattingSwitchHelper.getSwitchValue(idx,parameters));
}
}
}
idx++;
}
if ((getTarget() != null) && (getTarget().length() > 0) && ((getTarget().indexOf('/') > -1) || (getTarget().indexOf('\\') > -1) || (getTarget().indexOf('.') > -1) || (getTarget().indexOf(':') > -1))) {
setExternal(true);
}
}
