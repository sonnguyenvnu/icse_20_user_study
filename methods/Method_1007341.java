/** 
 * Modify GETFIELD, GETSTATIC, PUTFIELD, and PUTSTATIC so that a different field is accessed.  The new field must be declared in a superclass of the class in which the original field is declared.
 */
@Override public int transform(CtClass clazz,int pos,CodeIterator iterator,ConstPool cp){
  int c=iterator.byteAt(pos);
  if (c == GETFIELD || c == GETSTATIC || c == PUTFIELD || c == PUTSTATIC) {
    int index=iterator.u16bitAt(pos + 1);
    String typedesc=TransformReadField.isField(clazz.getClassPool(),cp,fieldClass,fieldname,isPrivate,index);
    if (typedesc != null) {
      if (newIndex == 0) {
        int nt=cp.addNameAndTypeInfo(newFieldname,typedesc);
        newIndex=cp.addFieldrefInfo(cp.addClassInfo(newClassname),nt);
        constPool=cp;
      }
      iterator.write16bit(newIndex,pos + 1);
    }
  }
  return pos;
}
