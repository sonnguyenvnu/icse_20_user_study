/** 
 * Concatenates two unique strings
 */
public UniqueString concat(UniqueString uniqueString){
  return uniqueStringOf(this.toString() + uniqueString.toString());
}
