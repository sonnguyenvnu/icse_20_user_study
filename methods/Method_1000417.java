private Pojo distinct(Pojo pojo,String names){
  if (!Lang.isEmpty(names) && names.length() != 0) {
    List<String> nameList=Arrays.asList(names.trim().split(","));
    if (names.toLowerCase().contains(DISTINCT)) {
      pojo.append(Pojos.Items.wrap(DISTINCT));
      for (int i=0; i < nameList.size(); ++i) {
        if (nameList.get(i).toLowerCase().contains(DISTINCT)) {
          Collections.swap(nameList,0,i);
          nameList.set(0,nameList.get(0).toLowerCase().replace(DISTINCT,"").toLowerCase());
          break;
        }
      }
    }
    StringBuilder sb=new StringBuilder();
    for (    String name : nameList) {
      sb.append(name.trim());
      sb.append("|");
    }
    sb.setLength(sb.length() - 1);
    pojo.getContext().setFieldMatcher(FieldMatcher.make(sb.toString(),null,true));
  }
  return pojo;
}
