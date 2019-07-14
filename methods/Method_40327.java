/** 
 * Returns the qname down to (and including) this component, ending with this component's name.  For instance, if this  {@code NQname}instance represents the  {@code foo} in {@code org.foo.bar}, this method will return  {@code org.foo}.
 */
public String thisQname(){
  Qname n=getTop();
  StringBuilder sb=new StringBuilder();
  sb.append(n.name.getId());
  while (n != this) {
    sb.append(".");
    n=n.next;
    sb.append(n.name.getId());
  }
  return sb.toString();
}
