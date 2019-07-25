private static IntervalWaitConstraint between(Duration notBeforeThan,Duration notLaterThan){
  return new IntervalWaitConstraint(notBeforeThan,notLaterThan);
}
