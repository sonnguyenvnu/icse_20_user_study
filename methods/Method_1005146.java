/** 
 * ???????
 */
@Transactional(readOnly=true) public List<TreeGrid> treegrid(List<?> all,TreeGridModel treeGridModel){
  return commonDao.treegrid(all,treeGridModel);
}
