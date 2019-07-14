/** 
 * ??????????
 * @param upmsSystemExample
 * @return
 */
@Override public List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample){
  return upmsSystemMapper.selectByExample(upmsSystemExample);
}
