private String hack(){
  if (sort == null && limit == -1)   return null;
  StringBuilder builder=new StringBuilder();
  if (sort != null)   builder.append(sort);
 else   builder.append(1);
  builder.append(" ");
  if (!ascending)   builder.append("DESC").append(" ");
  if (limit != -1)   builder.append("LIMIT").append(" ").append(limit);
  return builder.toString();
}
