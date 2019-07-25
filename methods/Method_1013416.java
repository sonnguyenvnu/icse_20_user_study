public final Object dequeue(){
  if (this.len == 0)   return null;
  Object res=this.states[this.start];
  this.states[this.start]=null;
  this.start=(this.start + 1) % this.states.length;
  this.len--;
  return res;
}
