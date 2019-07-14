/** 
 * Returns true if this method has public visibility. Non-private interface members are implicitly public, whether they declare the  {@code public} modifier ornot.
 */
@Override public boolean isPublic(){
  return isInterfaceMember() && !isPrivate() || super.isPublic();
}
