@Override public void init(){
  this.addButton(closeButton=new Button((this.width - this.backgroundWidth + 56) / 2,(this.height + this.backgroundHeight) / 2,200,20,"Close",button -> this.minecraft.player.closeScreen()));
}
