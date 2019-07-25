/** 
 * Returns the  {@link NamingConvention} that uses the user-provided names and tags as they are.
 */
public static NamingConvention identity(){
  return (name,type,baseUnit) -> name;
}
