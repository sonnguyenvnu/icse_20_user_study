public boolean valueChanged(){
  if ("int".equals(type)) {
    return (value.intValue() != newValue.intValue());
  }
 else   if ("hex".equals(type)) {
    return (value.intValue() != newValue.intValue());
  }
 else   if ("webcolor".equals(type)) {
    return (value.intValue() != newValue.intValue());
  }
 else {
    return (value.floatValue() != newValue.floatValue());
  }
}
