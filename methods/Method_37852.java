/** 
 * Defines arrays class loading strategy. If <code>false</code> (default), classes will be loaded by <code>Class.forName</code>. If <code>true</code>, classes will be loaded by reflection and component types.
 */
public void setLoadArrayClassByComponentTypes(final boolean loadArrayClassByComponentTypes){
  this.loadArrayClassByComponentTypes=loadArrayClassByComponentTypes;
}
