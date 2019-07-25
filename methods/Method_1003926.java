/** 
 * Equivalent to releasing a Connection with isValid() == false.
 * @see ObjectPool#remove(Object)
 */
@Override public void remove(Connection<T,E> connection){
  release(connection,true);
}
