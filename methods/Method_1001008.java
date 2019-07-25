/** 
 * <p>Rotate the point according to the current rotation of Earth.
 */
public Point3D rotate(Point3D pPoint){
  double lX=pPoint.getX();
  double lY=pPoint.getY();
  double lZ=pPoint.getZ();
  double _c_=fCosLon;
  double _s_=fSinLon;
  double _t_=_c_ * lX - _s_ * lZ;
  lZ=_s_ * lX + _c_ * lZ;
  lX=_t_;
  _c_=fCosLat;
  _s_=fSinLat;
  _t_=(_c_ * lY) - (_s_ * lZ);
  lZ=(_s_ * lY) + (_c_ * lZ);
  lY=_t_;
  _c_=fCosRot;
  _s_=fSinRot;
  _t_=(_c_ * lX) - (_s_ * lY);
  lY=(_s_ * lX) + (_c_ * lY);
  lX=_t_;
  return new Point3D(lX,lY,lZ);
}
