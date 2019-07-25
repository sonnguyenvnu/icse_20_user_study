public static IpfsWrapper build(Args args){
  Path ipfsDir=args.hasArg(IPFS_DIR) ? Paths.get(args.getArg(IPFS_DIR)) : args.fromPeergosDir(IPFS_DIR,DEFAULT_DIR_NAME);
  Path ipfsExe=getIpfsExePath(args);
  LOG().info("Using IPFS dir " + ipfsDir + " and IPFS binary " + ipfsExe);
  Config config=buildConfig(args);
  return new IpfsWrapper(ipfsExe,ipfsDir,config,new MultiAddress(args.getArg("proxy-target")));
}
