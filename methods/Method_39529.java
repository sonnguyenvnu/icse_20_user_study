/** 
 * Returns the internal of name of the super class (see  {@link Type#getInternalName()}). For interfaces, the super class is  {@link Object}.
 * @return the internal name of the super class, or {@literal null} for {@link Object} class.
 * @see ClassVisitor#visit(int,int,String,String,String,String[])
 */
public String getSuperName(){
  return readClass(header + 4,new char[maxStringLength]);
}
