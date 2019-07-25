public static void delete(final RendererConfiguration r,int delay){
  r.setActive(false);
  javax.swing.Timer t=new javax.swing.Timer(delay,new ActionListener(){
    @Override public void actionPerformed(    ActionEvent event){
      if (!r.isActive()) {
        LOGGER.debug("Deleting renderer " + r);
        if (r.gui != null) {
          r.gui.delete();
        }
        PMS.get().getFoundRenderers().remove(r);
        UPNPHelper.getInstance().removeRenderer(r);
        InetAddress ia=r.getAddress();
        if (addressAssociation.get(ia) == r) {
          addressAssociation.remove(ia);
        }
      }
    }
  }
);
  t.setRepeats(false);
  t.start();
}
