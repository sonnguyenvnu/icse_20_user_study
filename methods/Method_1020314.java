/** 
 * Registers an Object3D with this interface. The object is added to the global handle-to-object map, and the native finalization callback is set up. The handle of the object must already be set at this point!
 */
static final void register(Object3D obj){
  getInstance().liveObjects.put(new Long(obj.handle),new WeakReference(obj));
}
