public FindOne projection(String fields,Object... parameters){
  this.fields=queryFactory.createQuery(fields,parameters);
  return this;
}
