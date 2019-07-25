@Override public void update(Command command) throws IllegalArgumentException {
  PercentType oldvalue=(state == UnDefType.UNDEF) ? new PercentType() : (PercentType)state;
  if (command instanceof PercentType) {
    state=(PercentType)command;
  }
 else   if (command instanceof DecimalType) {
    double v=((DecimalType)command).doubleValue();
    v=(v - min) * 100.0 / (max - min);
    state=new PercentType(new BigDecimal(v));
  }
 else   if (command instanceof IncreaseDecreaseType) {
    if (((IncreaseDecreaseType)command) == IncreaseDecreaseType.INCREASE) {
      final double v=oldvalue.doubleValue() + step;
      state=new PercentType(new BigDecimal(v <= max ? v : max));
    }
 else {
      double v=oldvalue.doubleValue() - step;
      state=new PercentType(new BigDecimal(v >= min ? v : min));
    }
  }
 else   if (command instanceof OnOffType) {
    state=((OnOffType)command) == OnOffType.ON ? PercentType.HUNDRED : PercentType.ZERO;
  }
 else   if (command instanceof UpDownType) {
    if (((UpDownType)command) == UpDownType.UP) {
      final double v=oldvalue.doubleValue() + step;
      state=new PercentType(new BigDecimal(v <= max ? v : max));
    }
 else {
      final double v=oldvalue.doubleValue() - step;
      state=new PercentType(new BigDecimal(v >= min ? v : min));
    }
  }
 else   if (command instanceof StringType) {
    if (onValue != null && command.toString().equals(onValue)) {
      state=new PercentType(new BigDecimal(max));
    }
 else     if (offValue != null && command.toString().equals(offValue)) {
      state=new PercentType(new BigDecimal(min));
    }
 else {
      throw new IllegalStateException("Unknown String!");
    }
  }
 else {
    state=PercentType.valueOf(command.toString());
  }
}
