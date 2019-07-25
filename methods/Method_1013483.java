private final void put(int i,ModelValue elem){
  if (this.elems[i] == null && elem != null) {
    this.elems[i]=elem;
    this.count++;
  }
}
