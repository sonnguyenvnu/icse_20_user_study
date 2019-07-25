@Override public final void run(){
  if (!check.isEnabled()) {
    return;
  }
  try {
    Map<String,Optional<BigDecimal>> targetValues=targetChecker.check(check);
    DateTime now=new DateTime();
    BigDecimal warn=check.getWarn();
    BigDecimal error=check.getError();
    AlertType worstState;
    if (check.isAllowNoData()) {
      worstState=AlertType.OK;
    }
 else {
      worstState=AlertType.UNKNOWN;
    }
    List<Alert> interestingAlerts=new ArrayList<Alert>();
    for (    Entry<String,Optional<BigDecimal>> entry : targetValues.entrySet()) {
      String target=entry.getKey();
      Optional<BigDecimal> value=entry.getValue();
      if (!value.isPresent()) {
        if (!check.isAllowNoData()) {
          LOGGER.warn("No value present for {} and check must have data",target);
        }
        continue;
      }
      BigDecimal currentValue=value.get();
      Alert lastAlert=alertsStore.getLastAlertForTargetOfCheck(target,check.getId());
      AlertType lastState;
      if (lastAlert == null) {
        lastState=AlertType.OK;
      }
 else {
        lastState=lastAlert.getToType();
      }
      AlertType currentState=valueChecker.checkValue(currentValue,warn,error);
      if (currentState.isWorseThan(worstState)) {
        worstState=currentState;
      }
      if (isStillOk(lastState,currentState)) {
        continue;
      }
      Alert alert=createAlert(target,currentValue,warn,error,lastState,currentState,now);
      alertsStore.createAlert(check.getId(),alert);
      if (stateIsTheSame(lastState,currentState)) {
        continue;
      }
      interestingAlerts.add(alert);
    }
    Check updatedCheck=checksStore.updateStateAndLastCheck(check.getId(),worstState,DateTime.now());
    if (interestingAlerts.isEmpty()) {
      return;
    }
    for (    Subscription subscription : updatedCheck.getSubscriptions()) {
      if (!subscription.shouldNotify(now,worstState)) {
        continue;
      }
      for (      NotificationService notificationService : notificationServices) {
        if (notificationService.canHandle(subscription.getType())) {
          try {
            notificationService.sendNotification(updatedCheck,subscription,interestingAlerts);
          }
 catch (          Exception e) {
            LOGGER.warn("Notifying {} by {} failed.",subscription.getTarget(),subscription.getType(),e);
          }
        }
      }
    }
  }
 catch (  Exception e) {
    LOGGER.warn("{} failed",check.getName(),e);
  }
}
