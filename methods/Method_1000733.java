private void init(String keys,Object obj){
  this.keys=Lang.arrayFirst("obj",Strings.split(keys,false,'.'));
  this.val=obj;
  this.arrayItem=0;
}
