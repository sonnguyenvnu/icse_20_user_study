/** 
 * ( begin auto-generated from Server_active.xml ) Returns true if this server is still active and hasn't run into any trouble. ( end auto-generated )
 * @webref server:server
 * @brief Return true if this server is still active.
 */
public boolean active(){
  return thread != null;
}
