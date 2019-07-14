/** 
 * Compare 2 sets and check that one contains all members of the other.
 * @param target set of strings to check
 * @param members the members to compare to
 * @return true if all members are in the target
 */
public static boolean containsAll(Set<String> target,Set<String> members){
  target=new HashSet<String>(target);
  target.retainAll(members);
  return target.size() == members.size();
}
