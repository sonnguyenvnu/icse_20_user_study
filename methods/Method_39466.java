/** 
 * Core key-value addition.
 */
protected void justAdd(final String key,final String value,final ArrayList<String> keyProfiles,final Operator operator){
  if (operator == Operator.COPY) {
    HashMap<String,Object> target=new HashMap<>();
    String[] profiles=null;
    if (keyProfiles != null) {
      profiles=keyProfiles.toArray(new String[0]);
    }
    String[] sources=StringUtil.splitc(value,',');
    for (    String source : sources) {
      source=source.trim();
      String[] lookupProfiles=profiles;
      String lookupProfilesString=null;
      int leftIndex=source.indexOf('<');
      if (leftIndex != -1) {
        int rightIndex=source.indexOf('>');
        lookupProfilesString=source.substring(leftIndex + 1,rightIndex);
        source=source.substring(0,leftIndex).concat(source.substring(rightIndex + 1));
        lookupProfiles=StringUtil.splitc(lookupProfilesString,',');
        StringUtil.trimAll(lookupProfiles);
      }
      String[] wildcards=new String[]{source + ".*"};
      propsData.extract(target,lookupProfiles,wildcards,null);
      for (      Map.Entry<String,Object> entry : target.entrySet()) {
        String entryKey=entry.getKey();
        String suffix=entryKey.substring(source.length());
        String newKey=key + suffix;
        String newValue="${" + entryKey;
        if (lookupProfilesString != null) {
          newValue+="<" + lookupProfilesString + ">";
        }
        newValue+="}";
        if (profiles == null) {
          propsData.putBaseProperty(newKey,newValue,false);
        }
 else {
          for (          final String p : profiles) {
            propsData.putProfileProperty(newKey,newValue,p,false);
          }
        }
      }
    }
    return;
  }
  boolean append=operator == Operator.QUICK_APPEND;
  if (keyProfiles == null) {
    propsData.putBaseProperty(key,value,append);
    return;
  }
  for (  final String p : keyProfiles) {
    propsData.putProfileProperty(key,value,p,append);
  }
}
