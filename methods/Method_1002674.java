/** 
 * Returns a newly-created  {@link Backoff} based on the properties of this spec.
 */
Backoff build(){
  Backoff backoff;
  if (baseOption == BaseOption.fixed) {
    backoff=Backoff.fixed(fixedDelayMillis);
  }
 else   if (baseOption == BaseOption.random) {
    backoff=Backoff.random(randomMinDelayMillis,randomMaxDelayMillis);
  }
 else {
    backoff=Backoff.exponential(initialDelayMillis,maxDelayMillis,multiplier);
  }
  backoff=backoff.withJitter(minJitterRate,maxJitterRate);
  if (maxAttemptsConfigured) {
    backoff=backoff.withMaxAttempts(maxAttempts);
  }
  return backoff;
}
