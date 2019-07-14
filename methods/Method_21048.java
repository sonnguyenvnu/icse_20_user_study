/** 
 * Determines if params are for All Projects, i.e. discovery without params.
 * @return true if is All Projects.
 */
public boolean isAllProjects(){
  return isFalse(staffPicks()) && (starred() == null || starred() != 1) && (backed() == null || backed() != 1) && (social() == null || social() != 1) && category() == null && location() == null && isFalse(recommended());
}
