/** 
 * Helper for  {@link #equals} and{@link external.com.android.dx.rop.code.RegisterSpec.ForComparison#equals}, which actually does the test.
 * @param reg value of the instance variable, for another instance
 * @param type value of the instance variable, for another instance
 * @param local value of the instance variable, for another instance
 * @return whether this instance is equal to one with the givenvalues
 */
private boolean equals(int reg,TypeBearer type,LocalItem local){
  return (this.reg == reg) && this.type.equals(type) && ((this.local == local) || ((this.local != null) && this.local.equals(local)));
}
