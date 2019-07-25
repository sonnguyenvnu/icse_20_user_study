private Criteria filter(){
  return new Criteria(kclass).where(map(foreignKey,document.attributes().get("_id")));
}
