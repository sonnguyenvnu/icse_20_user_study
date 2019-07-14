@Override public void process(final StringBuilder out){
  if (objReference != null) {
    value=templateData.lookupObject(objReference);
  }
  if (value != null) {
    if (value instanceof Collection) {
      Collection collection=(Collection)value;
      int counter=0;
      for (      Object obj : collection) {
        if (counter > 0) {
          out.append(',').append(' ');
        }
        defineParameter(out,name + counter,obj,null);
        counter++;
      }
      return;
    }
  }
  defineParameter(out,name,value,null);
}
