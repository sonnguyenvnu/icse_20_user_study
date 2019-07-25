/** 
 * Adds the specified  {@link HealthChecker}s that determine the healthiness of the  {@link Server}.
 * @return {@code this}
 */
public HealthCheckServiceBuilder checkers(Iterable<? extends HealthChecker> healthCheckers){
  this.healthCheckers.addAll(requireNonNull(healthCheckers,"healthCheckers"));
  return this;
}
