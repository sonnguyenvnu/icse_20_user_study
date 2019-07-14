/** 
 * only invoke by asm byte
 * @return
 */
protected boolean writeDirect(JSONSerializer jsonBeanDeser){
  return jsonBeanDeser.out.writeDirect && this.writeDirect && jsonBeanDeser.writeDirect;
}
