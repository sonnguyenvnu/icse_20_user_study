/** 
 * Substitutes a class name in the given descriptor string.
 * @param desc    descriptor string
 * @param oldname replaced JVM class name
 * @param newname substituted JVM class name
 * @see Descriptor#toJvmName(String)
 */
public static String rename(String desc,String oldname,String newname){
  if (desc.indexOf(oldname) < 0)   return desc;
  StringBuffer newdesc=new StringBuffer();
  int head=0;
  int i=0;
  for (; ; ) {
    int j=desc.indexOf('L',i);
    if (j < 0)     break;
 else     if (desc.startsWith(oldname,j + 1) && desc.charAt(j + oldname.length() + 1) == ';') {
      newdesc.append(desc.substring(head,j));
      newdesc.append('L');
      newdesc.append(newname);
      newdesc.append(';');
      head=i=j + oldname.length() + 2;
    }
 else {
      i=desc.indexOf(';',j) + 1;
      if (i < 1)       break;
    }
  }
  if (head == 0)   return desc;
  int len=desc.length();
  if (head < len)   newdesc.append(desc.substring(head,len));
  return newdesc.toString();
}
