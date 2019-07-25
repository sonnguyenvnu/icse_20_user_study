@Override public Groups groups(){
  return Groups.combine(metric.map(Grouped::groups).orElseGet(Groups::empty),metadata.map(Grouped::groups).orElseGet(Groups::empty),suggest.map(Grouped::groups).orElseGet(Groups::empty));
}
