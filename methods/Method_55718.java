/** 
 * Returns true if the pointer member that corresponds to the specified  {@code memberOffset} is {@code NULL}. <p>This is useful to verify that not nullable members of an untrusted struct instance are indeed not  {@code NULL}.</p>
 * @param memberOffset the byte offset of the member to query
 * @return true if the member is {@code NULL}
 */
public boolean isNull(int memberOffset){
  if (DEBUG) {
    checkMemberOffset(memberOffset);
  }
  return memGetAddress(address() + memberOffset) == NULL;
}
