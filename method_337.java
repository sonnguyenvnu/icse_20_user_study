/** 
 * Sets the value of default user (login or user name).
 * @param v Value to assign to user.
 * @throws IllegalStateException if  {@link #getPooledConnection()} has been called
 */
public void _XXXXX_(final String v){
  assertInitializationAllowed();
  this.userName=v;
  update(connectionProperties,KEY_USER,v);
}