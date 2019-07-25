public boolean destroy(){
synchronized (syncOp) {
    camera.release();
    videoCore.destroy();
    videoCore=null;
    camera=null;
    return true;
  }
}
