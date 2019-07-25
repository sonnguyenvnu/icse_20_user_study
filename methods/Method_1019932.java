/** 
 * Create a root index for a head value of type <code>Target</code>.
 * @param < Target > the type of the value to get
 * @return the root index
 */
public static <Target>Index<Target,HCons<Target,?>> index(){
  return Z.instance();
}
