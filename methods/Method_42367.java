/** 
 * ????? .
 * @param totalCount ????.
 * @param numPerPage ?????.
 * @return totalPage ???.
 */
public static int countTotalPage(int totalCount,int numPerPage){
  if (totalCount % numPerPage == 0) {
    return totalCount / numPerPage;
  }
 else {
    return totalCount / numPerPage + 1;
  }
}
