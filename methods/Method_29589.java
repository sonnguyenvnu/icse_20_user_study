/** 
 * ???????
 * @param request
 * @param response
 * @param model
 * @return
 */
@RequestMapping("/overview") public ModelAndView overview(HttpServletRequest request,HttpServletResponse response,Model model){
  String ip=request.getParameter("ip");
  String date=request.getParameter("date");
  ServerInfo info=serverDataService.queryServerInfo(ip);
  if (info != null) {
    model.addAttribute("info",info);
    String ulimit=info.getUlimit();
    if (!StringUtils.isEmpty(ulimit)) {
      String[] tmp=ulimit.split(";");
      if (tmp.length == 2) {
        String[] a=tmp[0].split(",");
        if (a != null && a.length == 2) {
          if ("f".equals(a[0])) {
            model.addAttribute("file",a[1]);
          }
        }
        a=tmp[1].split(",");
        if (a != null && a.length == 2) {
          if ("p".equals(a[0])) {
            model.addAttribute("process",a[1]);
          }
        }
      }
    }
  }
  List<ServerStatus> list=serverDataService.queryServerOverview(ip,date);
  List<String> xAxis=new ArrayList<String>();
  float maxLoad1=0;
  double totalLoad1=0;
  float maxUser=0;
  float maxSys=0;
  float maxWa=0;
  float curFree=0;
  float maxUse=0;
  float maxCache=0;
  float maxBuffer=0;
  float maxSwapUse=0;
  float maxNetIn=0;
  float maxNetOut=0;
  int maxConn=0;
  int maxWait=0;
  int maxOrphan=0;
  float maxRead=0;
  float maxWrite=0;
  float maxBusy=0;
  float maxIops=0;
  Series<Float> load1Serie=new Series<Float>("1-min");
  Series<Float> load5Serie=new Series<Float>("5-min");
  Series<Float> load15Serie=new Series<Float>("15-min");
  Series<Float> userSerie=new Series<Float>("user");
  Series<Float> sysSerie=new Series<Float>("sys");
  Series<Float> waSerie=new Series<Float>("wa");
  Series<Float> totalSerie=new Series<Float>("total");
  Series<Float> useSerie=new Series<Float>("use");
  useSerie.setType("area");
  Series<Float> cacheSerie=new Series<Float>("cache");
  cacheSerie.setType("area");
  Series<Float> bufferSerie=new Series<Float>("buffer");
  bufferSerie.setType("area");
  Series<Float> swapSerie=new Series<Float>("total");
  Series<Float> swapUseSerie=new Series<Float>("use");
  Series<Float> netInSerie=new Series<Float>("in");
  Series<Float> netOutSerie=new Series<Float>("out");
  Series<Integer> establishedSerie=new Series<Integer>("established");
  Series<Integer> twSerie=new Series<Integer>("time wait");
  Series<Integer> orphanSerie=new Series<Integer>("orphan");
  Series<Float> readSerie=new Series<Float>("read");
  readSerie.setType("column");
  Series<Float> writeSerie=new Series<Float>("write");
  writeSerie.setType("column");
  Series<Float> busySerie=new Series<Float>("busy");
  busySerie.setYAxis(1);
  Series<Float> iopsSerie=new Series<Float>("iops");
  iopsSerie.setYAxis(2);
  for (int i=0; i < list.size(); ++i) {
    ServerStatus ss=list.get(i);
    xAxis.add(ss.getCtime().substring(0,2) + ":" + ss.getCtime().substring(2));
    load1Serie.addData(ss.getCload1());
    load5Serie.addData(ss.getCload5());
    load15Serie.addData(ss.getCload15());
    maxLoad1=getBigger(maxLoad1,ss.getCload1());
    totalLoad1+=ss.getCload1();
    userSerie.addData(ss.getCuser());
    sysSerie.addData(ss.getCsys());
    waSerie.addData(ss.getCwio());
    maxUser=getBigger(maxUser,ss.getCuser());
    maxSys=getBigger(maxSys,ss.getCsys());
    maxWa=getBigger(maxWa,ss.getCwio());
    totalSerie.addData(ss.getMtotal());
    float use=ss.getMtotal() - ss.getMfree() - ss.getMcache() - ss.getMbuffer();
    useSerie.addData(use);
    cacheSerie.addData(ss.getMcache());
    bufferSerie.addData(ss.getMbuffer());
    maxUse=getBigger(maxUse,use);
    maxCache=getBigger(maxCache,ss.getMcache());
    maxBuffer=getBigger(maxBuffer,ss.getMbuffer());
    if (i == list.size() - 1) {
      curFree=ss.getMtotal() - use;
    }
    swapSerie.addData(ss.getMswap());
    float swapUse=ss.getMswap() - ss.getMswapFree();
    swapUse=floor(swapUse);
    swapUseSerie.addData(swapUse);
    maxSwapUse=getBigger(maxSwapUse,swapUse);
    netInSerie.addData(ss.getNin());
    netOutSerie.addData(ss.getNout());
    maxNetIn=getBigger(maxNetIn,ss.getNin());
    maxNetOut=getBigger(maxNetOut,ss.getNout());
    establishedSerie.addData(ss.getTuse());
    twSerie.addData(ss.getTwait());
    orphanSerie.addData(ss.getTorphan());
    maxConn=getBigger(maxConn,ss.getTuse());
    maxWait=getBigger(maxWait,ss.getTwait());
    maxOrphan=getBigger(maxOrphan,ss.getTorphan());
    readSerie.addData(ss.getDread());
    writeSerie.addData(ss.getDwrite());
    busySerie.addData(ss.getDbusy());
    iopsSerie.addData(ss.getDiops());
    maxRead=getBigger(maxRead,ss.getDread());
    maxWrite=getBigger(maxWrite,ss.getDwrite());
    maxBusy=getBigger(maxBusy,ss.getDbusy());
    maxIops=getBigger(maxIops,ss.getDiops());
  }
  model.addAttribute("xAxis",JSON.toJSONString(xAxis));
  model.addAttribute("load1",JSON.toJSONString(load1Serie));
  model.addAttribute("load5",JSON.toJSONString(load5Serie));
  model.addAttribute("load15",JSON.toJSONString(load15Serie));
  model.addAttribute("maxLoad1",maxLoad1);
  model.addAttribute("avgLoad1",format(totalLoad1,list.size()));
  model.addAttribute("user",JSON.toJSONString(userSerie));
  model.addAttribute("sys",JSON.toJSONString(sysSerie));
  model.addAttribute("wa",JSON.toJSONString(waSerie));
  model.addAttribute("maxUser",maxUser);
  model.addAttribute("maxSys",maxSys);
  model.addAttribute("maxWa",maxWa);
  model.addAttribute("mtotal",JSON.toJSONString(totalSerie));
  model.addAttribute("muse",JSON.toJSONString(useSerie));
  model.addAttribute("mcache",JSON.toJSONString(cacheSerie));
  model.addAttribute("mbuffer",JSON.toJSONString(bufferSerie));
  model.addAttribute("curFree",format(curFree,1024));
  model.addAttribute("maxUse",format(maxUse,1024));
  model.addAttribute("maxCache",format(maxCache,1024));
  model.addAttribute("maxBuffer",format(maxBuffer,1024));
  model.addAttribute("mswap",JSON.toJSONString(swapSerie));
  model.addAttribute("mswapUse",JSON.toJSONString(swapUseSerie));
  model.addAttribute("maxSwap",maxSwapUse);
  model.addAttribute("nin",JSON.toJSONString(netInSerie));
  model.addAttribute("nout",JSON.toJSONString(netOutSerie));
  model.addAttribute("maxNetIn",format(maxNetIn,1024));
  model.addAttribute("maxNetOut",format(maxNetOut,1024));
  model.addAttribute("testab",JSON.toJSONString(establishedSerie));
  model.addAttribute("twait",JSON.toJSONString(twSerie));
  model.addAttribute("torph",JSON.toJSONString(orphanSerie));
  model.addAttribute("maxConn",maxConn);
  model.addAttribute("maxWait",maxWait);
  model.addAttribute("maxOrphan",maxOrphan);
  model.addAttribute("dread",JSON.toJSONString(readSerie));
  model.addAttribute("dwrite",JSON.toJSONString(writeSerie));
  model.addAttribute("dbusy",JSON.toJSONString(busySerie));
  model.addAttribute("diops",JSON.toJSONString(iopsSerie));
  model.addAttribute("maxRead",format(maxRead,1024));
  model.addAttribute("maxWrite",format(maxWrite,1024));
  model.addAttribute("maxBusy",maxBusy);
  model.addAttribute("maxIops",maxIops);
  model.addAttribute("date",date);
  return new ModelAndView("server/overview");
}
