/** 
 * Specifies a  {@link HealthCheckUpdateHandler} which handles other HTTP methods than {@code HEAD} and{@code GET} which updates the healthiness of the {@link Server}. This feature is disabled by default. It is recommended to employ some authorization mechanism such as  {@link HttpAuthService}when enabling this feature.
 * @param updateHandler The {@link HealthCheckUpdateHandler} which handles {@code PUT},  {@code POST} or{@code PATCH} requests and tells if the {@link Server} needs to be marked ashealthy or unhealthy.
 * @see #updatable(boolean)
 */
public HealthCheckServiceBuilder updatable(HealthCheckUpdateHandler updateHandler){
  this.updateHandler=requireNonNull(updateHandler,"updateHandler");
  return this;
}
