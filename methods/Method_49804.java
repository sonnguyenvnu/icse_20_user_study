public short getFillDefault(){
  String fillDefault=mSmilElement.getAttribute(FILLDEFAULT_ATTRIBUTE_NAME);
  if (fillDefault.equalsIgnoreCase(FILL_REMOVE_ATTRIBUTE)) {
    return FILL_REMOVE;
  }
 else   if (fillDefault.equalsIgnoreCase(FILL_FREEZE_ATTRIBUTE)) {
    return FILL_FREEZE;
  }
 else   if (fillDefault.equalsIgnoreCase(FILL_AUTO_ATTRIBUTE)) {
    return FILL_AUTO;
  }
 else   if (fillDefault.equalsIgnoreCase(FILL_HOLD_ATTRIBUTE)) {
    return FILL_FREEZE;
  }
 else   if (fillDefault.equalsIgnoreCase(FILL_TRANSITION_ATTRIBUTE)) {
    return FILL_FREEZE;
  }
 else {
    ElementTime parent=getParentElementTime();
    if (parent == null) {
      return FILL_AUTO;
    }
 else {
      return ((ElementTimeImpl)parent).getFillDefault();
    }
  }
}
