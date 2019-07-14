@Override public String convert(Object value){
  Preconditions.checkNotNull(value);
  if (value instanceof String)   return (String)value;
 else   if (value instanceof Namifiable)   return ((Namifiable)value).name();
 else   return value.toString();
}
