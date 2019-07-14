private static Duration pertubTime(Duration duration){
  Duration newDuration=duration.dividedBy((int)(2.0 / (1 + (random.nextDouble() * 2 - 1.0) * PERTURBATION_PERCENTAGE)));
  assert !duration.isZero() : duration;
  return newDuration;
}
