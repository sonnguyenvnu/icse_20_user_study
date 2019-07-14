/** 
 * Counts the total number of properties, including all profiles. This operation performs calculation each time and it might be more time consuming then expected.
 */
public int countTotalProperties(){
  return data.countBaseProperties() + data.countProfileProperties();
}
