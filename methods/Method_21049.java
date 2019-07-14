/** 
 * Determines if params are for Saved Projects, i.e. discovery with starred params.
 * @return true if is Saved Projects.
 */
public boolean isSavedProjects(){
  return isTrue(starred() != null && starred() == 1) && isFalse(staffPicks()) && (backed() == null || backed() != 1) && (social() == null || social() != 1) && category() == null && location() == null && isFalse(recommended());
}
