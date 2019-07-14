protected HighlightBuilder.Field parseHighlightField(Object[] params){
  if (params == null || params.length == 0 || params.length > 2) {
  }
  HighlightBuilder.Field field=new HighlightBuilder.Field(params[0].toString());
  if (params.length == 1) {
    return field;
  }
  Map<String,Object> highlightParams=(Map<String,Object>)params[1];
  for (  Map.Entry<String,Object> param : highlightParams.entrySet()) {
switch (param.getKey()) {
case "type":
      field.highlighterType((String)param.getValue());
    break;
case "boundary_chars":
  field.boundaryChars(fromArrayListToCharArray((ArrayList)param.getValue()));
break;
case "boundary_max_scan":
field.boundaryMaxScan((Integer)param.getValue());
break;
case "force_source":
field.forceSource((Boolean)param.getValue());
break;
case "fragmenter":
field.fragmenter((String)param.getValue());
break;
case "fragment_offset":
field.fragmentOffset((Integer)param.getValue());
break;
case "fragment_size":
field.fragmentSize((Integer)param.getValue());
break;
case "highlight_filter":
field.highlightFilter((Boolean)param.getValue());
break;
case "matched_fields":
field.matchedFields((String[])((ArrayList)param.getValue()).toArray(new String[((ArrayList)param.getValue()).size()]));
break;
case "no_match_size":
field.noMatchSize((Integer)param.getValue());
break;
case "num_of_fragments":
field.numOfFragments((Integer)param.getValue());
break;
case "order":
field.order((String)param.getValue());
break;
case "phrase_limit":
field.phraseLimit((Integer)param.getValue());
break;
case "post_tags":
field.postTags((String[])((ArrayList)param.getValue()).toArray(new String[((ArrayList)param.getValue()).size()]));
break;
case "pre_tags":
field.preTags((String[])((ArrayList)param.getValue()).toArray(new String[((ArrayList)param.getValue()).size()]));
break;
case "require_field_match":
field.requireFieldMatch((Boolean)param.getValue());
break;
}
}
return field;
}
