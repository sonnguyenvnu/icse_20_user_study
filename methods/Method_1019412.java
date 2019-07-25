/** 
 * Constructs a Socks5 instance without any parameter.
 */
private void init(){
  acceptableMethods=new ArrayList<>();
  acceptableMethods.add(new NoAuthenticationRequiredMethod());
  acceptableMethods.add(new GssApiMethod());
  acceptableMethods.add(new UsernamePasswordMethod());
}
