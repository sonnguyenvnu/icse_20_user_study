/** 
 * Calculates the first item index of requested page.
 */
public static int calcFirstItemIndexOfPage(int page,final int pageSize,final int total){
  if (total == 0) {
    return 0;
  }
  if (page < 1) {
    page=1;
  }
  int first=(page - 1) * pageSize;
  if (first >= total) {
    first=((total - 1) / pageSize) * pageSize;
  }
  return first;
}
