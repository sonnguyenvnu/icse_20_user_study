/** 
 * Returns an instance that is the intersection between this instance and the given one, if any. The intersection is defined as follows: <ul> <li>If  {@code other} is {@code null}, then the result is  {@code null}. <li>If the register numbers don't match, then the intersection is  {@code null}. Otherwise, the register number of the intersection is the same as the one in the two instances.</li> <li>If the types returned by  {@code getType()} are not{@code equals()}, then the intersection is null.</li> <li>If the type bearers returned by  {@code getTypeBearer()}are  {@code equals()}, then the intersection's type bearer is the one from this instance. Otherwise, the intersection's type bearer is the  {@code getType()} of this instance.</li><li>If the locals are  {@code equals()}, then the local info of the intersection is the local info of this instance. Otherwise, the local info of the intersection is  {@code null}.</li> </ul>
 * @param other {@code null-ok;} instance to intersect with (or {@code null})
 * @param localPrimary whether local variables are primary to theintersection; if  {@code true}, then the only non-null results occur when registers being intersected have equal local infos (or both have  {@code null} local infos)
 * @return {@code null-ok;} the intersection
 */
public RegisterSpec intersect(RegisterSpec other,boolean localPrimary){
  if (this == other) {
    return this;
  }
  if ((other == null) || (reg != other.getReg())) {
    return null;
  }
  LocalItem resultLocal=((local == null) || !local.equals(other.getLocalItem())) ? null : local;
  boolean sameName=(resultLocal == local);
  if (localPrimary && !sameName) {
    return null;
  }
  Type thisType=getType();
  Type otherType=other.getType();
  if (thisType != otherType) {
    return null;
  }
  TypeBearer resultTypeBearer=type.equals(other.getTypeBearer()) ? type : thisType;
  if ((resultTypeBearer == type) && sameName) {
    return this;
  }
  return (resultLocal == null) ? make(reg,resultTypeBearer) : make(reg,resultTypeBearer,resultLocal);
}
