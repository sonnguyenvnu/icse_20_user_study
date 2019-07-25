/** 
 * Create a new  {@link DependencyGroup} instance with the given name.
 * @param name the name of the group
 * @return a new {@link DependencyGroup} instance
 */
public static DependencyGroup create(String name){
  DependencyGroup group=new DependencyGroup();
  group.setName(name);
  return group;
}
