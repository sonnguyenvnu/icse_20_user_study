/** 
 * Pop last element from the path.
 */
public CharSequence pop(){
  if (altPath != null) {
    altPath.pop();
  }
  return paths[--index];
}
