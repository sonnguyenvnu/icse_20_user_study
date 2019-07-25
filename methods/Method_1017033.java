@Override public Filter optimize(){
  return filter().visit(new Visitor<Filter>(){
    @Override public Filter visitNot(    final NotFilter not){
      return not.filter().optimize();
    }
    @Override public Filter defaultAction(    final Filter filter){
      return NotFilter.create(filter.optimize());
    }
  }
);
}
