/** 
 * Returns a randomly chosen host name. This is used to pick one host when multiple are configured
 * @return
 */
protected String getSingleHostname(){
  return hostnames[random.nextInt(hostnames.length)];
}
