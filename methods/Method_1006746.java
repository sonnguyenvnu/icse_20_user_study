/** 
 * ????????? ???Iterator(Object obj = it.next()) ???HashMap?size? size?????????ConcurrentModificationException?
 */
@SuppressWarnings({"unchecked","rawtypes"}) public static void iterator(){
  List a1=new ArrayList<String>();
  a1.add("List01");
  a1.add("List02");
  a1.add("List04");
  a1.add("List05");
  Iterator i1=a1.iterator();
  while (i1.hasNext()) {
    Object obj=i1.next();
    if (obj.equals("List02"))     a1.add("List03");
  }
  System.out.print("???\n\t" + a1 + "\n");
}
