/** 
 * @return address at which server is running
 */
@Nullable private String getFTPAddressString(){
  InetAddress ia=FtpService.getLocalInetAddress(getContext());
  if (ia == null)   return null;
  return (getSecurePreference() ? FtpService.INITIALS_HOST_SFTP : FtpService.INITIALS_HOST_FTP) + ia.getHostAddress() + ":" + getDefaultPortFromPreferences();
}
