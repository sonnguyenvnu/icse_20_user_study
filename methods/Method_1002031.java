private ST_shape_desc __Shapes__(String s,ST_shape_functions shape_functions,ST_polygon_t polygon){
  ST_shape_desc result=new ST_shape_desc();
  result.setPtr("name",s == null ? null : new CString(s));
  result.setPtr("fns",shape_functions);
  result.setPtr("polygon",polygon);
  return result;
}
