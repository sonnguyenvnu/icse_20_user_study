/** 
 * Accesses the parameter with the name of the capturing group.
 */
@Get("regex:^/regex/(?<name>.*)$") public String regex(@Param String name){
  return "regex: " + name;
}
