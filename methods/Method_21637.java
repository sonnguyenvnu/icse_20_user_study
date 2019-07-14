private void setSize(AggregationBuilder agg,Field field){
  if (field instanceof MethodField) {
    MethodField mf=((MethodField)field);
    Object customSize=mf.getParamsAsMap().get("size");
    if (customSize == null) {
      if (select.getRowCount() > 0) {
        if (agg instanceof TermsAggregationBuilder) {
          ((TermsAggregationBuilder)agg).size(select.getRowCount());
        }
      }
    }
 else {
    }
  }
 else {
    if (select.getRowCount() > 0) {
      if (agg instanceof TermsAggregationBuilder) {
        ((TermsAggregationBuilder)agg).size(select.getRowCount());
      }
    }
  }
}
