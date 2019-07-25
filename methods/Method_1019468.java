@Override HllSketchImpl copy(){
  return Hll8Array.heapify(mem);
}
