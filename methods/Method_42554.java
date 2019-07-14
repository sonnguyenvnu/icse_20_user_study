/** 
 * ????????
 * @param mistakeList
 */
public void saveListDate(List<RpAccountCheckMistake> mistakeList){
  for (  RpAccountCheckMistake mistake : mistakeList) {
    rpAccountCheckMistakeDao.insert(mistake);
  }
}
