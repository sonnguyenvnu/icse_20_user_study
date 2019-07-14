protected Object[] makeKeys(Project project,Object o,int index){
  Object[] keys=new Object[_keyMakers.length];
  for (int i=0; i < keys.length; i++) {
    keys[i]=makeKey(project,_keyMakers[i],_criteria[i],o,index);
  }
  return keys;
}
