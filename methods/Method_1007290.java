/** 
 * Substitutes class names in the given descriptor string according to the given <code>map</code>.
 * @param map a map between replaced and substitutedJVM class names.
 * @see Descriptor#toJvmName(String)
 */
public static String rename(String desc,Map<String,String> map){
  if (map == null)   return desc;
  StringBuffer newdesc=new StringBuffer();
  int head=0;
  int i=0;
  for (; ; ) {
    int j=desc.indexOf('L',i);
    if (j < 0)     break;
    int k=desc.indexOf(';',j);
    if (k < 0)     break;
    i=k + 1;
    String name=desc.substring(j + 1,k);
    String name2=map.get(name);
    if (name2 != null) {
      newdesc.append(desc.substring(head,j));
      newdesc.append('L');
      newdesc.append(name2);
      newdesc.append(';');
      head=i;
    }
  }
  if (head == 0)   return desc;
  int len=desc.length();
  if (head < len)   newdesc.append(desc.substring(head,len));
  return newdesc.toString();
}
