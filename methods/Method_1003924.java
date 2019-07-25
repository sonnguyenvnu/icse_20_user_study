/** 
 * Equivalent to releasing a Connection with isValid() == false.
 * @see ObjectPool#remove(Object)
 */
@Override public void remove(S connection){
  release(connection,true);
}
