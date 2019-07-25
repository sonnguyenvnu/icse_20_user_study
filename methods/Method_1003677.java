/** 
 * Creates an imposition that forces the use of an ephemeral port.
 * @return an imposition for an ephemeral port
 */
public static ForceServerListenPortImposition ephemeral(){
  return of(0);
}
