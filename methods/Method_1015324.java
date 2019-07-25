@Override public void update(){
  WechatClient client=(WechatClient)imPanel.getClient();
  WXContactTreeNode root=(WXContactTreeNode)getRoot();
  root.removeAllChildren();
  if ("recent".equals(name)) {
    List<Contact> list=client.getRecentList();
    if (list != null) {
synchronized (this) {
        Collections.sort(list);
      }
      for (      Contact target : list) {
        WXContactTreeNode cn=new WXContactTreeNode(target);
        root.add(cn);
      }
    }
  }
 else   if ("friend".equals(name)) {
    List<VirtualCategory<Contact>> categories=getContactGroup(client.getMemberList());
    if (categories != null) {
      categories.add(0,new VirtualCategory<>("groups",client.getGroupList()));
      for (      VirtualCategory<Contact> c : categories) {
        WXContactTreeNode cn=new WXContactTreeNode(c);
        root.add(cn);
        if (c.list != null) {
          for (          Contact f : c.list) {
            WXContactTreeNode fn=new WXContactTreeNode(f);
            cn.add(fn);
          }
        }
      }
    }
  }
 else   if ("group".equals(name)) {
    List<Contact> list=client.getGroupList();
    if (list != null) {
      for (      Contact r : list) {
        WXContactTreeNode cn=new WXContactTreeNode(r);
        root.add(cn);
      }
    }
  }
 else   if ("public".equals(name)) {
    List<Contact> list=client.getPublicUsersList();
    if (list != null) {
      for (      Contact r : list) {
        WXContactTreeNode cn=new WXContactTreeNode(r);
        root.add(cn);
      }
    }
  }
}
