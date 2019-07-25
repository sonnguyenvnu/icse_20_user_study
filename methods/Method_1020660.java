/** 
 * ??????
 * @param authentication ??
 * @return ????
 */
@RequestMapping("/user") public Object user(Authentication authentication){
  if (authentication != null) {
    return authentication.getPrincipal();
  }
  return null;
}
