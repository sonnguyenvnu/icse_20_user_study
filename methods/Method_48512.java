public static StaticBuffer[] getBounds(RelationCategory type,boolean systemTypes){
  int start, end;
switch (type) {
case PROPERTY:
    start=DirectionID.PROPERTY_DIR.getPrefix(systemTypes,systemTypes);
  end=start;
break;
case EDGE:
start=DirectionID.EDGE_OUT_DIR.getPrefix(systemTypes,systemTypes);
end=start;
break;
case RELATION:
start=DirectionID.PROPERTY_DIR.getPrefix(systemTypes,systemTypes);
end=DirectionID.EDGE_OUT_DIR.getPrefix(systemTypes,systemTypes);
break;
default :
throw new AssertionError("Unrecognized type:" + type);
}
end++;
assert end > start;
return new StaticBuffer[]{getPrefixed(start),getPrefixed(end)};
}
