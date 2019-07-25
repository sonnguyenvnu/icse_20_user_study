@Override public Void parse(Object value) throws IOException {
  String optionsString=StringUtils.remove(toString(value),' ');
  if (optionsString != null) {
    int idxFirstSep=optionsString.indexOf(",");
    if (idxFirstSep == -1) {
      text=optionsString;
      optionsString="";
    }
 else {
      text=optionsString.substring(0,idxFirstSep);
      optionsString=optionsString.substring(idxFirstSep + 1);
    }
    String[] options=StringUtils.split(optionsString,",");
    String[] availableSymbols=getAvailableSymbols(channel);
    String[] availableBeepOptions=getAvailableOptions(channel,DATAPOINT_NAME_BEEP);
    String[] availableBacklightOptions=getAvailableOptions(channel,DATAPOINT_NAME_BACKLIGHT);
    String[] availableUnitOptions=getAvailableOptions(channel,DATAPOINT_NAME_UNIT);
    String deviceAddress=channel.getDevice().getAddress();
    if (logger.isDebugEnabled()) {
      logger.debug("Remote control '{}' supports these beep options: {}",deviceAddress,availableBeepOptions);
      logger.debug("Remote control '{}' supports these backlight options: {}",deviceAddress,availableBacklightOptions);
      logger.debug("Remote control '{}' supports these unit options: {}",deviceAddress,availableUnitOptions);
      logger.debug("Remote control '{}' supports these symbols: {}",deviceAddress,symbols);
    }
    if (options != null) {
      for (      String parameter : options) {
        logger.debug("Parsing remote control option '{}'",parameter);
        beep=getIntParameter(availableBeepOptions,beep,parameter,DATAPOINT_NAME_BEEP,deviceAddress);
        backlight=getIntParameter(availableBacklightOptions,backlight,parameter,DATAPOINT_NAME_BACKLIGHT,deviceAddress);
        unit=getIntParameter(availableUnitOptions,unit,parameter,DATAPOINT_NAME_UNIT,deviceAddress);
        if (ArrayUtils.contains(availableSymbols,parameter)) {
          logger.debug("Symbol '{}' found for remote control '{}'",parameter,deviceAddress);
          symbols.add(parameter);
        }
      }
    }
  }
  return null;
}
