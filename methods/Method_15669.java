private SimpleUserToken checkTimeout(SimpleUserToken detail){
  if (null == detail) {
    return null;
  }
  if (detail.getMaxInactiveInterval() <= 0) {
    return detail;
  }
  if (System.currentTimeMillis() - detail.getLastRequestTime() > detail.getMaxInactiveInterval()) {
    changeTokenState(detail,TokenState.expired);
    return detail;
  }
  return detail;
}
