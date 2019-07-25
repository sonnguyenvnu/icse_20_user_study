private Criteria filter(){
  return new Criteria(kclass).where(map("_id",document.attributes().get(foreignKey)));
}
