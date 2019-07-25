/** 
 * ??????and??
 * @param query
 * @param source
 * @param dest hql((this_.0 like ? and this_.1 like ?) or this_.2 like ?) ???cq.add(cq.or(cq.and(cq, 0, 1), cq, 2))
 * @return
 */
public Criterion and(CriteriaQuery query,int source,int dest){
  return Restrictions.and(query.getCriterionList().getParas(source),query.getCriterionList().getParas(dest));
}
