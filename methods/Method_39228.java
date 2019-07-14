/** 
 * Returns string representation of this.
 * @return String representation of this.
 */
@Override public String toString(){
  if (this.personalName == null) {
    return this.email;
  }
  return this.personalName + " <" + this.email + '>';
}
