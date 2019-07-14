private ShapeBuilder getShapeBuilderFromString(String str) throws IOException {
  String json;
  if (str.contains("{"))   json=fixJsonFromElastic(str);
 else   json=WktToGeoJsonConverter.toGeoJson(trimApostrophes(str));
  return getShapeBuilderFromJson(json);
}
