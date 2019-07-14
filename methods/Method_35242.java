/** 
 * Returns the already instantiated Router in the specified position or  {@code null} if thereis no router associated with this position.
 */
@Nullable public Router getRouter(int position){
  return visibleRouters.get(position);
}
