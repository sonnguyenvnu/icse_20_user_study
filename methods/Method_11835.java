public static PotentialAssignment forValue(final String name,final Object value){
  return new PotentialAssignment(){
    @Override public Object getValue(){
      return value;
    }
    @Override public String toString(){
      return format("[%s]",value);
    }
    @Override public String getDescription(){
      String valueString;
      if (value == null) {
        valueString="null";
      }
 else {
        try {
          valueString=format("\"%s\"",value);
        }
 catch (        Throwable e) {
          valueString=format("[toString() threw %s: %s]",e.getClass().getSimpleName(),e.getMessage());
        }
      }
      return format("%s <from %s>",valueString,name);
    }
  }
;
}
