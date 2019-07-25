public static SplitVpnAdapter establish(IntraVpnService vpnService){
  ParcelFileDescriptor tunFd=establishVpn(vpnService);
  if (tunFd == null) {
    return null;
  }
  return new SplitVpnAdapter(vpnService,tunFd);
}
