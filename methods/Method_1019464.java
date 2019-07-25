@Override HllSketchImpl copy(){
  return Hll4Array.heapify(mem);
}
