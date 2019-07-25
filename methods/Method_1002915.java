/** 
 * Access the parameter with the index number.
 */
@Get("glob:/glob/**") public String glob(@Param("0") String name){
  return "glob: " + name;
}
