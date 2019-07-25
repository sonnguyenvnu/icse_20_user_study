String dump(){
  dumped=true;
  String str=". " + CustomLogicModel.escape(name) + " 0 " + sizeX + " " + sizeY + " " + extList.size() + " ";
  int i;
  for (i=0; i != extList.size(); i++) {
    ExtListEntry ent=extList.get(i);
    if (i > 0)     str+=" ";
    str+=CustomLogicModel.escape(ent.name) + " " + ent.node + " " + ent.pos + " " + ent.side;
  }
  str+=" " + CustomLogicModel.escape(nodeList) + " " + CustomLogicModel.escape(elmDump);
  return str;
}
