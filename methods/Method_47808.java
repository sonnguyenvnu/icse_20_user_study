private void copyFieldFromCursor(T record,Field field,Cursor c,int index) throws IllegalAccessException {
  if (field.getType().isAssignableFrom(Integer.class))   field.set(record,c.getInt(index));
 else   if (field.getType().isAssignableFrom(Long.class))   field.set(record,c.getLong(index));
 else   if (field.getType().isAssignableFrom(Double.class))   field.set(record,c.getDouble(index));
 else   if (field.getType().isAssignableFrom(String.class))   field.set(record,c.getString(index));
 else   throw new RuntimeException("Type not supported: " + field.getType().getName() + " " + field.getName());
}
