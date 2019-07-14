/** 
 * Determines if the candidate should be accepted into the main space, as determined by its frequency relative to the victim. A small amount of randomness is used to protect against hash collision attacks, where the victim's frequency is artificially raised so that no new entries are admitted.
 * @param candidateKey the key for the entry being proposed for long term retention
 * @param victimKey the key for the entry chosen by the eviction policy for replacement
 * @return if the candidate should be admitted and the victim ejected
 */
@GuardedBy("evictionLock") boolean admit(K candidateKey,K victimKey){
  int victimFreq=frequencySketch().frequency(victimKey);
  int candidateFreq=frequencySketch().frequency(candidateKey);
  if (candidateFreq > victimFreq) {
    return true;
  }
 else   if (candidateFreq <= 5) {
    return false;
  }
  int random=ThreadLocalRandom.current().nextInt();
  return ((random & 127) == 0);
}
