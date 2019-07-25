private void updated(GenericAlert alert){
  this.alertsById.computeIfPresent(alert.getId(),(id,ref) -> {
    ref.set(alert);
    return ref;
  }
);
}
