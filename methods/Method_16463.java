/** 
 * ??????????
 * @return ??????
 * @see Optional
 * @see Authentication#getAttribute(String)
 */
static Optional<PersonnelAuthentication> current(){
  return Optional.ofNullable(PersonnelAuthenticationHolder.get());
}
