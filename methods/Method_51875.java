/** 
 * Returns true if this method is abstract, so doesn't declare a body. Interface members are implicitly abstract, whether they declare the {@code abstract} modifier or not. Default interfacemethods are not abstract though, consistently with the standard reflection API.
 */
@Override public boolean isAbstract(){
  return isInterfaceMember() && !isDefault() || super.isAbstract();
}
