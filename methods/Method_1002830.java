/** 
 * Specifies whether the healthiness of the  {@link Server} can be updated by sending a {@code PUT}, {@code POST} or {@code PATCH} request to the {@link HealthCheckService}. This feature is disabled by default. If enabled, a JSON object which has a boolean property named  {@code "healthy"} can besent using a  {@code PUT} or {@code POST} request. A JSON patch in a {@code PATCH} request is alsoaccepted. It is recommended to employ some authorization mechanism such as  {@link HttpAuthService}when enabling this feature.
 * @return {@code this}
 * @see #updatable(HealthCheckUpdateHandler)
 */
public HealthCheckServiceBuilder updatable(boolean updatable){
  if (updatable) {
    return updatable(DefaultHealthCheckUpdateHandler.INSTANCE);
  }
  updateHandler=null;
  return this;
}
