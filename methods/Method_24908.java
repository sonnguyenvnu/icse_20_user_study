/** 
 * Add an item that will be moved from size() into the settings() method. This needs to be the exact version of the statement so that it can be matched against and removed from the size() method in the code.
 */
public void addStatement(String stmt){
  statements.append(stmt);
}
