/** 
 * ( begin auto-generated from Client_active.xml ) Returns true if this client is still active and hasn't run into any trouble. ( end auto-generated )
 * @webref client:client
 * @brief Returns true if this client is still active
 * @usage application
 */
public boolean active(){
  return (thread != null);
}
