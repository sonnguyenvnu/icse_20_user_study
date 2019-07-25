static final double estimate(final long thetaLong,final int curCount,final boolean empty){
  if (estMode(thetaLong,empty)) {
    final double theta=thetaLong / MAX_THETA_LONG_AS_DOUBLE;
    return curCount / theta;
  }
  return curCount;
}
