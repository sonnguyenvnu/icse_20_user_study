/** 
 * Create a clone of the important parts of the given token.  Note that this does NOT copy the hiddenBefore and hiddenAfter fields.
 * @param t   token to partially clone
 * @return    newly created partial clone
 */
public CommonHiddenStreamToken partialCloneToken(CommonHiddenStreamToken t){
  CommonHiddenStreamToken u=new CommonHiddenStreamToken(t.getType(),t.getText());
  u.setColumn(t.getColumn());
  u.setLine(t.getLine());
  u.setFilename(t.getFilename());
  return u;
}
