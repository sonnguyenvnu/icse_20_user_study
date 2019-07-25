@Override public synchronized void hold(AlertEntity alertEntity){
  if (!alertEntity.getAlertType().equals(alertType)) {
    throw new IllegalArgumentException(String.format("need alert type: %s, but receive: %s",alertType.name(),alertEntity.getAlertType().name()));
  }
  alerts.remove(alertEntity);
  alerts.add(alertEntity);
}
