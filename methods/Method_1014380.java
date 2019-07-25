/** 
 * Updates the color value.
 * @return Returns the color value as HSB/HSV string (hue, saturation, brightness) eg. "60, 100, 100".If rgb is enabled, an RGB string (red,green,blue) will be returned instead. red,green,blue are within [0,255].
 */
@Override public void update(Command command) throws IllegalArgumentException {
  HSBType oldvalue=(state == UnDefType.UNDEF) ? new HSBType() : (HSBType)state;
  if (command instanceof HSBType) {
    state=(HSBType)command;
  }
 else   if (command instanceof OnOffType) {
    OnOffType boolValue=((OnOffType)command);
    PercentType minOn=new PercentType(Math.max(oldvalue.getBrightness().intValue(),onBrightness));
    state=new HSBType(oldvalue.getHue(),oldvalue.getSaturation(),boolValue == OnOffType.ON ? minOn : new PercentType(0));
  }
 else   if (command instanceof PercentType) {
    state=new HSBType(oldvalue.getHue(),oldvalue.getSaturation(),(PercentType)command);
  }
 else {
    final String updatedValue=command.toString();
    if (onValue.equals(updatedValue)) {
      PercentType minOn=new PercentType(Math.max(oldvalue.getBrightness().intValue(),onBrightness));
      state=new HSBType(oldvalue.getHue(),oldvalue.getSaturation(),minOn);
    }
 else     if (offValue.equals(updatedValue)) {
      state=new HSBType(oldvalue.getHue(),oldvalue.getSaturation(),new PercentType(0));
    }
 else     if (isRGB) {
      String[] split=updatedValue.split(",");
      if (split.length != 3) {
        throw new IllegalArgumentException(updatedValue + " is not a valid RGB syntax");
      }
      state=HSBType.fromRGB(Integer.parseInt(split[0]),Integer.parseInt(split[1]),Integer.parseInt(split[2]));
    }
 else {
      state=new HSBType(updatedValue);
    }
  }
}
