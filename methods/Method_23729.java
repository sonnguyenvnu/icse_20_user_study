/** 
 * Returns the actual array being used to store the data. Suitable for iterating with a for() loop, but modifying the list could cause terrible things to happen.
 */
public String[] values(){
  crop();
  return data;
}
