/** 
 * Returns total number of http connections in the pool. 
 */
public synchronized int _XXXXX_(){
  int total=0;
  for (  Connection connection : connections) {
    if (!connection.isSpdy())     total++;
  }
  return total;
}