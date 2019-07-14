public void resize(int wide,int high){
  dispose();
  Texture tex=new Texture(pg,wide,high,getParameters());
  tex.set(this);
  copyObject(tex);
  tempFbo=null;
}
