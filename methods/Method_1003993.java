/** 
 * Creates a set of credentials for the given authentication  {@code scheme}.
 * @param scheme the scheme to authenticate with
 * @param authToken the authentication token
 * @return a set of credentials that can be used to authenticate the zoo keeper client
 */
public static Credentials credentials(final String scheme,final byte[] authToken){
  MorePreconditions.checkNotBlank(scheme);
  Preconditions.checkNotNull(authToken);
  return new Credentials(){
    @Override public void authenticate(    ZooKeeper zooKeeper){
      zooKeeper.addAuthInfo(scheme,authToken);
    }
    @Override public String scheme(){
      return scheme;
    }
    @Override public byte[] authToken(){
      return authToken;
    }
    @Override public boolean equals(    Object o){
      if (!(o instanceof Credentials)) {
        return false;
      }
      Credentials other=(Credentials)o;
      return new EqualsBuilder().append(scheme,other.scheme()).append(authToken,other.authToken()).isEquals();
    }
    @Override public int hashCode(){
      return Objects.hashCode(scheme,authToken);
    }
  }
;
}
