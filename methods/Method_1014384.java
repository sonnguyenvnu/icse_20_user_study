@Override public void update(Command command) throws IllegalArgumentException {
  DecimalType oldvalue=(state == UnDefType.UNDEF) ? new DecimalType() : (DecimalType)state;
  BigDecimal newValue;
  if (command instanceof DecimalType) {
    if (!checkConditions(((DecimalType)command).toBigDecimal(),oldvalue)) {
      return;
    }
    state=(DecimalType)command;
  }
 else   if (command instanceof IncreaseDecreaseType || command instanceof UpDownType) {
    if (command == IncreaseDecreaseType.INCREASE || command == UpDownType.UP) {
      newValue=oldvalue.toBigDecimal().add(step);
    }
 else {
      newValue=oldvalue.toBigDecimal().subtract(step);
    }
    if (!checkConditions(newValue,oldvalue)) {
      return;
    }
    state=new DecimalType(newValue);
  }
 else {
    newValue=new BigDecimal(command.toString());
    if (!checkConditions(newValue,oldvalue)) {
      return;
    }
    state=new DecimalType(newValue);
  }
}
