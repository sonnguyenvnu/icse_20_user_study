/** 
 * ?????buffer
 */
public void loseWeight(){
  if (size == data.length) {
    return;
  }
  int[] newData=new int[size];
  System.arraycopy(this.data,0,newData,0,size);
  this.data=newData;
}
