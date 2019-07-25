@Override public Iterable<Element> _apply(final String line){
  final String[] fields=extractFields(line);
  if (null == fields) {
    return Collections.emptyList();
  }
  final FreqMap vehicleCountsByType=getVehicleCounts(fields);
  final Date startDate=getDate(fields[dCount.ordinal()],fields[Hour.ordinal()]);
  final Date endDate=null != startDate ? DateUtils.addHours(startDate,1) : null;
  final String region=fields[Region_Name.ordinal()];
  final String location=fields[ONS_LA_Name.ordinal()];
  final String road=fields[Road.ordinal()];
  final String junctionA=road + ":" + fields[A_Junction.ordinal()];
  final String junctionB=road + ":" + fields[B_Junction.ordinal()];
  final String junctionALocation=fields[A_Ref_E.ordinal()] + "," + fields[A_Ref_N.ordinal()];
  final String junctionBLocation=fields[B_Ref_E.ordinal()] + "," + fields[B_Ref_N.ordinal()];
  final List<Edge> edges=Arrays.asList(new Edge.Builder().group(ElementGroup.REGION_CONTAINS_LOCATION).source(region).dest(location).directed(true).build(),new Edge.Builder().group(ElementGroup.LOCATION_CONTAINS_ROAD).source(location).dest(road).directed(true).build(),new Edge.Builder().group(ElementGroup.ROAD_HAS_JUNCTION).source(road).dest(junctionA).directed(true).build(),new Edge.Builder().group(ElementGroup.ROAD_HAS_JUNCTION).source(road).dest(junctionB).directed(true).build(),new Edge.Builder().group(ElementGroup.JUNCTION_LOCATED_AT).source(junctionA).dest(junctionALocation).directed(true).build(),new Edge.Builder().group(ElementGroup.JUNCTION_LOCATED_AT).source(junctionB).dest(junctionBLocation).directed(true).build(),new Edge.Builder().group(ElementGroup.ROAD_USE).source(junctionA).dest(junctionB).directed(true).property("startDate",startDate).property("endDate",endDate).property("count",getTotalCount(vehicleCountsByType)).property("countByVehicleType",vehicleCountsByType).build());
  final List<Entity> entities=Arrays.asList(new Entity.Builder().group(ElementGroup.JUNCTION_USE).vertex(junctionA).property("countByVehicleType",vehicleCountsByType).property("endDate",endDate).property("startDate",startDate).property("count",getTotalCount(vehicleCountsByType)).build(),new Entity.Builder().group(ElementGroup.JUNCTION_USE).vertex(junctionB).property("countByVehicleType",vehicleCountsByType).property("endDate",endDate).property("startDate",startDate).property("count",getTotalCount(vehicleCountsByType)).build());
  final List<Entity> cardinalityEntities=createCardinalities(edges);
  return new ChainedIterable<>(edges,entities,cardinalityEntities);
}
