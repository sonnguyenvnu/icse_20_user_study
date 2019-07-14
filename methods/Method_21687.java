private AggregationBuilder makeRangeGroup(MethodField field) throws SqlParseException {
switch (field.getName().toLowerCase()) {
case "range":
    return rangeBuilder(field);
case "date_histogram":
  return dateHistogram(field);
case "date_range":
return dateRange(field);
case "month":
return dateRange(field);
case "histogram":
return histogram(field);
case "geohash_grid":
return geohashGrid(field);
case "geo_bounds":
return geoBounds(field);
case "terms":
return termsAgg(field);
default :
throw new SqlParseException("can define this method " + field);
}
}
