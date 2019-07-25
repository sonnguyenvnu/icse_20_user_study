/** 
 * Check if I'm in memberList, too 
 */
public boolean authenticate(AuthToken token,Message msg){
  if ((token != null) && (token instanceof FixedMembershipToken) && (this.memberList != null)) {
    PhysicalAddress src=(PhysicalAddress)auth.down(new Event(Event.GET_PHYSICAL_ADDRESS,msg.getSrc()));
    if (src == null) {
      log.error(Util.getMessage("DidnTFindPhysicalAddressFor") + msg.getSrc());
      return false;
    }
    return isInMembersList((IpAddress)src);
  }
  if (log.isWarnEnabled())   log.warn("Invalid AuthToken instance - wrong type or null");
  return false;
}
