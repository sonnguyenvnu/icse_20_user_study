/** 
 * ???
 * @param date
 * @return
 */
@SuppressWarnings("deprecation") public static int getQuarter(Date date){
  if (date.getMonth() == 0 || date.getMonth() == 1 || date.getMonth() == 2) {
    return 1;
  }
 else   if (date.getMonth() == 3 || date.getMonth() == 4 || date.getMonth() == 5) {
    return 2;
  }
 else   if (date.getMonth() == 6 || date.getMonth() == 7 || date.getMonth() == 8) {
    return 3;
  }
 else   if (date.getMonth() == 9 || date.getMonth() == 10 || date.getMonth() == 11) {
    return 4;
  }
 else {
    return 0;
  }
}
