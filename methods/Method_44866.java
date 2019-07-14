@Override public void onSimulatedExchangeOperation() throws IOException {
  int val=random.nextInt(1000);
  if (val == 1) {
    throw new NonceException("Exchanges often complain about nonce issues. " + GENERIC_GUIDE);
  }
 else   if (val == 2) {
    throw new SocketTimeoutException("Socket timeouts connecting to exchanges are commonplace. " + GENERIC_GUIDE);
  }
 else   if (val == 3) {
    throw new SystemOverloadException("System overloads are a common error on some exchanges. " + GENERIC_GUIDE);
  }
  if (!rateLimiter.tryAcquire()) {
    if (RandomUtils.nextBoolean()) {
      throw new RateLimitExceededException(RATE_LIMIT_EXCEEDED);
    }
 else {
      throw new FrequencyLimitExceededException(RATE_LIMIT_EXCEEDED);
    }
  }
}
