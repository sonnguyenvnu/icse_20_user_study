public List fetch(){
  sql=select + EMPTY_STRING + "from" + EMPTY_STRING + entity + " as " + defaultName + EMPTY_STRING + joins + EMPTY_STRING + where + EMPTY_STRING + group + EMPTY_STRING + order + EMPTY_STRING;
  Query query=em().createQuery(sql);
  for (  String obj : bindings.keySet()) {
    query.setParameter(obj,bindings.get(obj));
  }
  query.setFirstResult(offset);
  if (limit != -1)   query.setMaxResults(limit);
  return query.getResultList();
}
