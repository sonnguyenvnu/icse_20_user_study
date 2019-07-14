/** 
 * ??????????
 */
public static String formatName(List<PersonBean> casts){
  if (casts != null && casts.size() > 0) {
    StringBuilder stringBuilder=new StringBuilder();
    for (int i=0; i < casts.size(); i++) {
      if (i < casts.size() - 1) {
        stringBuilder.append(casts.get(i).getName()).append(" / ");
      }
 else {
        stringBuilder.append(casts.get(i).getName());
      }
    }
    return stringBuilder.toString();
  }
 else {
    return "??";
  }
}
