public static ColumnDef build(String name,String charset,String type,short pos,boolean signed,String enumValues[],Long columnLength){
  name=name.intern();
  if (charset != null)   charset=charset.intern();
switch (type) {
case "tinyint":
case "smallint":
case "mediumint":
case "int":
    return new IntColumnDef(name,type,pos,signed);
case "bigint":
  return new BigIntColumnDef(name,type,pos,signed);
case "tinytext":
case "text":
case "mediumtext":
case "longtext":
case "varchar":
case "char":
return new StringColumnDef(name,type,pos,charset);
case "tinyblob":
case "blob":
case "mediumblob":
case "longblob":
case "binary":
case "varbinary":
return new StringColumnDef(name,type,pos,"binary");
case "geometry":
case "geometrycollection":
case "linestring":
case "multilinestring":
case "multipoint":
case "multipolygon":
case "polygon":
case "point":
return new GeometryColumnDef(name,type,pos);
case "float":
case "double":
return new FloatColumnDef(name,type,pos);
case "decimal":
return new DecimalColumnDef(name,type,pos);
case "date":
return new DateColumnDef(name,type,pos);
case "datetime":
case "timestamp":
return new DateTimeColumnDef(name,type,pos,columnLength);
case "time":
return new TimeColumnDef(name,type,pos,columnLength);
case "year":
return new YearColumnDef(name,type,pos);
case "enum":
return new EnumColumnDef(name,type,pos,enumValues);
case "set":
return new SetColumnDef(name,type,pos,enumValues);
case "bit":
return new BitColumnDef(name,type,pos);
case "json":
return new JsonColumnDef(name,type,pos);
default :
throw new IllegalArgumentException("unsupported column type " + type);
}
}
