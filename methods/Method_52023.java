/** 
 * Returns the packages in outer-to-inner order. This is specific to Java's package structure. If the outer class is in the unnamed package, returns an empty list. <p> {@literal @NotNull}
 * @return The packages.
 */
public List<String> getPackageList(){
  return packages.reverse();
}
