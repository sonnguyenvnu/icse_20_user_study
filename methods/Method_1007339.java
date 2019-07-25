protected int match(int c,int pos,CodeIterator iterator,int typedesc,ConstPool cp) throws BadBytecode {
  if (newIndex == 0) {
    int nt=cp.addNameAndTypeInfo(cp.addUtf8Info(newMethodname),typedesc);
    int ci=cp.addClassInfo(newClassname);
    if (c == INVOKEINTERFACE)     newIndex=cp.addInterfaceMethodrefInfo(ci,nt);
 else {
      if (newMethodIsPrivate && c == INVOKEVIRTUAL)       iterator.writeByte(INVOKESPECIAL,pos);
      newIndex=cp.addMethodrefInfo(ci,nt);
    }
    constPool=cp;
  }
  iterator.write16bit(newIndex,pos + 1);
  return pos;
}
