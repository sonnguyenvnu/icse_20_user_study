public static void remediate(Style s){
  if (s.getStyleId() == null) {
    if (log.isWarnEnabled()) {
      log.warn("Style is missing ID(!)");
      log.warn(XmlUtils.marshaltoString(s));
    }
    if (s.getName() != null && s.getName().getVal() != null) {
      log.warn("remediating");
      s.setStyleId(s.getName().getVal());
    }
 else {
      log.warn(".. ignoring");
      return;
    }
  }
  if (s.getType() == null) {
    log.warn("Style is missing type");
    if (s.getBasedOn() != null && s.getBasedOn().getVal() != null && s.getBasedOn().getVal().equals("Normal")) {
      log.warn("remediating");
      s.setType("paragraph");
    }
 else {
      log.warn(".. ignoring");
      return;
    }
  }
}
