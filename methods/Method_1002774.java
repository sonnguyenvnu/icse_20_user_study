@SuppressWarnings("unchecked") @Override public <T>Attribute<T> attr(AttributeKey<T> key){
  if (key == null) {
    throw new NullPointerException("key");
  }
  AtomicReferenceArray<DefaultAttribute<?>> attributes=this.attributes;
  if (attributes == null) {
    attributes=new AtomicReferenceArray<>(BUCKET_SIZE);
    if (!updater.compareAndSet(this,null,attributes)) {
      attributes=this.attributes;
    }
  }
  int i=index(key);
  DefaultAttribute<?> head=attributes.get(i);
  if (head == null) {
    head=new DefaultAttribute();
    DefaultAttribute<T> attr=new DefaultAttribute<>(head,key);
    head.next=attr;
    attr.prev=head;
    if (attributes.compareAndSet(i,null,head)) {
      return attr;
    }
 else {
      head=attributes.get(i);
    }
  }
synchronized (head) {
    DefaultAttribute<?> curr=head;
    for (; ; ) {
      DefaultAttribute<?> next=curr.next;
      if (next == null) {
        DefaultAttribute<T> attr=new DefaultAttribute<>(head,key);
        curr.next=attr;
        attr.prev=curr;
        return attr;
      }
      if (next.key == key && !next.removed) {
        return (Attribute<T>)next;
      }
      curr=next;
    }
  }
}
