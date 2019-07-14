/** 
 * Adds a long constructor assignment. 
 */
private void addTimeConstructorAssignment(MethodSpec.Builder constructor,String field){
  constructor.addStatement("$T.UNSAFE.putLong(this, $N, $N)",UNSAFE_ACCESS,offsetName(field),"now");
}
