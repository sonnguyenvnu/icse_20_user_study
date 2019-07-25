private String trim(String name){
  if (isBeginName(name) || isInsideName(name))   return (name.substring(2));
 else   return name;
}
