@Override HllSketchImpl copy(){
  return Hll6Array.heapify(mem);
}
