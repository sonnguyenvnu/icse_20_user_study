@Activate protected void activate(){
  types.add(new ExtensionType("binding","Bindings"));
  types.add(new ExtensionType("ui","User Interfaces"));
  types.add(new ExtensionType("persistence","Persistence Services"));
  for (  ExtensionType type : types) {
    for (int i=0; i < 10; i++) {
      String id=type.getId() + Integer.toString(i);
      boolean installed=Math.random() > 0.5;
      String name=RandomStringUtils.randomAlphabetic(5);
      String label=name + " " + StringUtils.capitalize(type.getId());
      String typeId=type.getId();
      String version="1.0";
      String link=(Math.random() < 0.5) ? null : "http://lmgtfy.com/?q=" + name;
      String description=createDescription();
      String imageLink=null;
      String backgroundColor=createRandomColor();
      Extension extension=new Extension(id,typeId,label,version,link,installed,description,backgroundColor,imageLink);
      extensions.put(extension.getId(),extension);
    }
  }
}
