/** 
 * Test if this DHCP lease includes vendor hint that network link is metered, and sensitive to heavy data transfers.
 */
public boolean hasMeteredHint(){
  if (vendorInfo != null) {
    return vendorInfo.contains("ANDROID_METERED");
  }
 else {
    return false;
  }
}
