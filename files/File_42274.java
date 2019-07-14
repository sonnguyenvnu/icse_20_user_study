package org.horaapps.leafpic.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 手势图片控件
 *
 * @author clifford
 */
public class PinchImageView extends ImageView {

    ////////////////////////////////�?置�?�数////////////////////////////////

    /**
     * 图片缩放动画时间
     */
    public static final int SCALE_ANIMATOR_DURATION = 200;

    /**
     * 惯性动画衰�?�?�数
     */
    public static final float FLING_DAMPING_FACTOR = 0.9f;

    /**
     * 图片最大放大比例
     */
    private static final float MAX_SCALE = 4f;

    ////////////////////////////////监�?�器////////////////////////////////

    /**
     * 外界点击事件
     *
     * @see #setOnClickListener(OnClickListener)
     */
    private OnClickListener mOnClickListener;

    /**
     * 外界长按事件
     *
     * @see #setOnLongClickListener(OnLongClickListener)
     */
    private OnLongClickListener mOnLongClickListener;

    @Override
    public void setOnClickListener(OnClickListener l) {
        //默认的click会在任何点击情况下都会触�?�，所以�?��?自己的
        mOnClickListener = l;
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        //默认的long click会在任何长按情况下都会触�?�，所以�?��?自己的
        mOnLongClickListener = l;
    }

    ////////////////////////////////公共状�?获�?�////////////////////////////////

    /**
     * 手势状�?：自由状�?
     *
     * @see #getPinchMode()
     */
    public static final int PINCH_MODE_FREE = 0;

    /**
     * 手势状�?：�?�指滚动状�?
     *
     * @see #getPinchMode()
     */
    public static final int PINCH_MODE_SCROLL = 1;

    /**
     * 手势状�?：�?�指缩放状�?
     *
     * @see #getPinchMode()
     */
    public static final int PINCH_MODE_SCALE = 2;

    /**
     * 外层�?��?�矩阵，如果是�?��?矩阵，那么图片是fit center状�?
     *
     * @see #getOuterMatrix(Matrix)
     * @see #outerMatrixTo(Matrix, long)
     */
    private Matrix mOuterMatrix = new Matrix();

    /**
     * 矩形�?�罩
     *
     * @see #getMask()
     * @see #zoomMaskTo(RectF, long)
     */
    private RectF mMask;

    /**
     * 当�?手势状�?
     *
     * @see #getPinchMode()
     * @see #PINCH_MODE_FREE
     * @see #PINCH_MODE_SCROLL
     * @see #PINCH_MODE_SCALE
     */
    private int mPinchMode = PINCH_MODE_FREE;

    /**
     * 获�?�外部�?��?�矩阵.
     * <p>
     * 外部�?��?�矩阵记录了图片手势�?作的最终结果,是相对于图片fit center状�?的�?��?�.
     * 默认值为�?��?矩阵,此时图片为fit center状�?.
     *
     * @param matrix 用于填充结果的对象
     * @return 如果传了matrix�?�数则将matrix填充�?�返回, �?�则new一个填充返回
     */
    public Matrix getOuterMatrix(Matrix matrix) {
        if (matrix == null) {
            matrix = new Matrix(mOuterMatrix);
        } else {
            matrix.set(mOuterMatrix);
        }
        return matrix;
    }

    /**
     * 获�?�内部�?��?�矩阵.
     * <p>
     * 内部�?��?�矩阵是原图到fit center状�?的�?��?�,当原图尺寸�?�化或者控件大�?�?�化都会�?�生改�?�
     * 当尚未布局或者原图�?存在时,其值无�?义.所以在调用�?需�?确�?�?置�?�件有效,�?�则将影�?计算结果.
     *
     * @param matrix 用于填充结果的对象
     * @return 如果传了matrix�?�数则将matrix填充�?�返回, �?�则new一个填充返回
     */
    public Matrix getInnerMatrix(Matrix matrix) {
        if (matrix == null) {
            matrix = new Matrix();
        } else {
            matrix.reset();
        }
        if (isReady()) {
            //原图大�?
            RectF tempSrc = MathUtils.rectFTake(0, 0, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
            //控件大�?
            RectF tempDst = MathUtils.rectFTake(0, 0, getWidth(), getHeight());
            //计算fit center矩阵
            matrix.setRectToRect(tempSrc, tempDst, Matrix.ScaleToFit.CENTER);
            //释放临时对象
            MathUtils.rectFGiven(tempDst);
            MathUtils.rectFGiven(tempSrc);
        }
        return matrix;
    }

    /**
     * 获�?�图片总�?��?�矩阵.
     * <p>
     * 总�?��?�矩阵为内部�?��?�矩阵x外部�?��?�矩阵,决定了原图到所�?最终状�?的�?��?�
     * 当尚未布局或者原图�?存在时,其值无�?义.所以在调用�?需�?确�?�?置�?�件有效,�?�则将影�?计算结果.
     *
     * @param matrix 用于填充结果的对象
     * @return 如果传了matrix�?�数则将matrix填充�?�返回, �?�则new一个填充返回
     * @see #getOuterMatrix(Matrix)
     * @see #getInnerMatrix(Matrix)
     */
    public Matrix getCurrentImageMatrix(Matrix matrix) {
        //获�?�内部�?��?�矩阵
        matrix = getInnerMatrix(matrix);
        //乘上外部�?��?�矩阵
        matrix.postConcat(mOuterMatrix);
        return matrix;
    }

    /**
     * 获�?�当�?�?��?��?�的图片�?置和尺寸
     * <p>
     * 当尚未布局或者原图�?存在时,其值无�?义.所以在调用�?需�?确�?�?置�?�件有效,�?�则将影�?计算结果.
     *
     * @param rectF 用于填充结果的对象
     * @return 如果传了rectF�?�数则将rectF填充�?�返回, �?�则new一个填充返回
     * @see #getCurrentImageMatrix(Matrix)
     */
    public RectF getImageBound(RectF rectF) {
        if (rectF == null) {
            rectF = new RectF();
        } else {
            rectF.setEmpty();
        }
        if (!isReady()) {
            return rectF;
        } else {
            //申请一个空matrix
            Matrix matrix = MathUtils.matrixTake();
            //获�?�当�?总�?��?�矩阵
            getCurrentImageMatrix(matrix);
            //对原图矩形进行�?��?�得到当�?显示矩形
            rectF.set(0, 0, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
            matrix.mapRect(rectF);
            //释放临时matrix
            MathUtils.matrixGiven(matrix);
            return rectF;
        }
    }

    /**
     * 获�?�当�?设置的mask
     *
     * @return 返回当�?的mask对象副本, 如果当�?没有设置mask则返回null
     */
    public RectF getMask() {
        if (mMask != null) {
            return new RectF(mMask);
        } else {
            return null;
        }
    }

    /**
     * 获�?�当�?手势状�?
     *
     * @see #PINCH_MODE_FREE
     * @see #PINCH_MODE_SCROLL
     * @see #PINCH_MODE_SCALE
     */
    public int getPinchMode() {
        return mPinchMode;
    }

    /**
     * 与ViewPager结�?�的时候使用
     *
     * @param direction
     * @return
     */
    @Override
    public boolean canScrollHorizontally(int direction) {
        if (mPinchMode == PinchImageView.PINCH_MODE_SCALE) {
            return true;
        }
        RectF bound = getImageBound(null);
        if (bound == null) {
            return false;
        }
        if (bound.isEmpty()) {
            return false;
        }
        if (direction > 0) {
            return bound.right > getWidth();
        } else {
            return bound.left < 0;
        }
    }

    /**
     * 与ViewPager结�?�的时候使用
     *
     * @param direction
     * @return
     */
    @Override
    public boolean canScrollVertically(int direction) {
        if (mPinchMode == PinchImageView.PINCH_MODE_SCALE) {
            return true;
        }
        RectF bound = getImageBound(null);
        if (bound == null) {
            return false;
        }
        if (bound.isEmpty()) {
            return false;
        }
        if (direction > 0) {
            return bound.bottom > getHeight();
        } else {
            return bound.top < 0;
        }
    }

    ////////////////////////////////公共状�?设置////////////////////////////////

    /**
     * 执行当�?outerMatrix到指定outerMatrix�?�?�的动画
     * <p>
     * 调用此方法会�?�止正在进行中的手势以�?�手势动画.
     * 当duration为0时,outerMatrix值会被立�?�设置而�?会�?�动动画.
     *
     * @param endMatrix 动画目标矩阵
     * @param duration  动画�?续时间
     * @see #getOuterMatrix(Matrix)
     */
    public void outerMatrixTo(Matrix endMatrix, long duration) {
        if (endMatrix == null) {
            return;
        }
        //将手势设置为PINCH_MODE_FREE将�?�止�?�续手势的触�?�
        mPinchMode = PINCH_MODE_FREE;
        //�?�止所有正在进行的动画
        cancelAllAnimator();
        //如果时间�?�?�法立�?�执行结果
        if (duration <= 0) {
            mOuterMatrix.set(endMatrix);
            dispatchOuterMatrixChanged();
            invalidate();
        } else {
            //创建矩阵�?�化动画
            mScaleAnimator = new ScaleAnimator(mOuterMatrix, endMatrix, duration);
            mScaleAnimator.start();
        }
    }

    /**
     * 执行当�?mask到指定mask的�?�化动画
     * <p>
     * 调用此方法�?会�?�止手势以�?�手势相关动画,但会�?�止正在进行的mask动画.
     * 当�?mask为null时,则�?执行动画立�?�设置为目标mask.
     * 当duration为0时,立�?�将当�?mask设置为目标mask,�?会执行动画.
     *
     * @param mask     动画目标mask
     * @param duration 动画�?续时间
     * @see #getMask()
     */
    public void zoomMaskTo(RectF mask, long duration) {
        if (mask == null) {
            return;
        }
        //�?�止mask动画
        if (mMaskAnimator != null) {
            mMaskAnimator.cancel();
            mMaskAnimator = null;
        }
        //如果duration为0或者之�?没有设置过mask,�?执行动画,立�?�设置
        if (duration <= 0 || mMask == null) {
            if (mMask == null) {
                mMask = new RectF();
            }
            mMask.set(mask);
            invalidate();
        } else {
            //执行mask动画
            mMaskAnimator = new MaskAnimator(mMask, mask, duration);
            mMaskAnimator.start();
        }
    }

    /**
     * �?置所有状�?
     * <p>
     * �?置�?置到fit center状�?,清空mask,�?�止所有手势,�?�止所有动画.
     * 但�?清空drawable,以�?�事件绑定相关数�?�.
     */
    public void reset() {
        //�?置�?置到fit
        mOuterMatrix.reset();
        dispatchOuterMatrixChanged();
        //清空mask
        mMask = null;
        //�?�止所有手势
        mPinchMode = PINCH_MODE_FREE;
        mLastMovePoint.set(0, 0);
        mScaleCenter.set(0, 0);
        mScaleBase = 0;
        //�?�止所有动画
        if (mMaskAnimator != null) {
            mMaskAnimator.cancel();
            mMaskAnimator = null;
        }
        cancelAllAnimator();
        //�?绘
        invalidate();
    }

    ////////////////////////////////对外广播事件////////////////////////////////

    /**
     * 外部矩阵�?�化事件通知监�?�器
     */
    public interface OuterMatrixChangedListener {

        /**
         * 外部矩阵�?�化回调
         * <p>
         * 外部矩阵的任何�?�化�?�都收到此回调.
         * 外部矩阵�?�化�?�,总�?�化矩阵,图片的展示�?置都将�?�生�?�化.
         *
         * @param pinchImageView
         * @see #getOuterMatrix(Matrix)
         * @see #getCurrentImageMatrix(Matrix)
         * @see #getImageBound(RectF)
         */
        void onOuterMatrixChanged(PinchImageView pinchImageView);
    }

    /**
     * 所有OuterMatrixChangedListener监�?�列表
     *
     * @see #addOuterMatrixChangedListener(OuterMatrixChangedListener)
     * @see #removeOuterMatrixChangedListener(OuterMatrixChangedListener)
     */
    private List<OuterMatrixChangedListener> mOuterMatrixChangedListeners;

    /**
     * 当mOuterMatrixChangedListeners被�?定�?�?许修改时,临时将修改写到这个副本中
     *
     * @see #mOuterMatrixChangedListeners
     */
    private List<OuterMatrixChangedListener> mOuterMatrixChangedListenersCopy;

    /**
     * mOuterMatrixChangedListeners的修改�?定
     * <p>
     * 当进入dispatchOuterMatrixChanged方法时,被加1,退出�?被�?1
     *
     * @see #dispatchOuterMatrixChanged()
     * @see #addOuterMatrixChangedListener(OuterMatrixChangedListener)
     * @see #removeOuterMatrixChangedListener(OuterMatrixChangedListener)
     */
    private int mDispatchOuterMatrixChangedLock;

    /**
     * 添加外部矩阵�?�化监�?�
     *
     * @param listener
     */
    public void addOuterMatrixChangedListener(OuterMatrixChangedListener listener) {
        if (listener == null) {
            return;
        }
        //如果监�?�列表没有被修改�?定直接将监�?�添加到监�?�列表
        if (mDispatchOuterMatrixChangedLock == 0) {
            if (mOuterMatrixChangedListeners == null) {
                mOuterMatrixChangedListeners = new ArrayList<OuterMatrixChangedListener>();
            }
            mOuterMatrixChangedListeners.add(listener);
        } else {
            //如果监�?�列表修改被�?定,那么�?试在监�?�列表副本上添加
            //监�?�列表副本将会在�?定被解除时替�?�到监�?�列表里
            if (mOuterMatrixChangedListenersCopy == null) {
                if (mOuterMatrixChangedListeners != null) {
                    mOuterMatrixChangedListenersCopy = new ArrayList<OuterMatrixChangedListener>(mOuterMatrixChangedListeners);
                } else {
                    mOuterMatrixChangedListenersCopy = new ArrayList<OuterMatrixChangedListener>();
                }
            }
            mOuterMatrixChangedListenersCopy.add(listener);
        }
    }

    /**
     * 删除外部矩阵�?�化监�?�
     *
     * @param listener
     */
    public void removeOuterMatrixChangedListener(OuterMatrixChangedListener listener) {
        if (listener == null) {
            return;
        }
        //如果监�?�列表没有被修改�?定直接在监�?�列表数�?�结构上修改
        if (mDispatchOuterMatrixChangedLock == 0) {
            if (mOuterMatrixChangedListeners != null) {
                mOuterMatrixChangedListeners.remove(listener);
            }
        } else {
            //如果监�?�列表被修改�?定,那么就在其副本上修改
            //其副本将会在�?定解除时替�?�回监�?�列表
            if (mOuterMatrixChangedListenersCopy == null) {
                if (mOuterMatrixChangedListeners != null) {
                    mOuterMatrixChangedListenersCopy = new ArrayList<OuterMatrixChangedListener>(mOuterMatrixChangedListeners);
                }
            }
            if (mOuterMatrixChangedListenersCopy != null) {
                mOuterMatrixChangedListenersCopy.remove(listener);
            }
        }
    }

    /**
     * 触�?�外部矩阵修改事件
     * <p>
     * 需�?在�?次给外部矩阵设置值时都调用此方法.
     *
     * @see #mOuterMatrix
     */
    private void dispatchOuterMatrixChanged() {
        if (mOuterMatrixChangedListeners == null) {
            return;
        }
        //增加�?
        //这里之所以用计数器�?��?定是因为�?�能在�?定期间�?�间接调用了此方法产生递归
        //使用boolean无法判断递归结�?�
        mDispatchOuterMatrixChangedLock++;
        //在列表循环过程中�?�?许修改列表,�?�则将引�?�崩溃
        for (OuterMatrixChangedListener listener : mOuterMatrixChangedListeners) {
            listener.onOuterMatrixChanged(this);
        }
        //�?�?
        mDispatchOuterMatrixChangedLock--;
        //如果是递归的情况,mDispatchOuterMatrixChangedLock�?�能大于1,�?�有�?到0�?能算列表的�?定解除
        if (mDispatchOuterMatrixChangedLock == 0) {
            //如果期间有修改列表,那么副本将�?为null
            if (mOuterMatrixChangedListenersCopy != null) {
                //将副本替�?�掉正�?的列表
                mOuterMatrixChangedListeners = mOuterMatrixChangedListenersCopy;
                //清空副本
                mOuterMatrixChangedListenersCopy = null;
            }
        }
    }

    ////////////////////////////////用于�?载定制////////////////////////////////

    /**
     * 获�?�图片最大�?�放大的比例
     * <p>
     * 如果放大大于这个比例则�?被�?许.
     * 在�?�手缩放过程中如果图片放大比例大于这个值,手指释放将回弹到这个比例.
     * 在�?�击放大过程中�?�?许放大比例大于这个值.
     * 覆盖此方法�?�以定制�?�?�情况使用�?�?�的最大�?�放大比例.
     *
     * @return 缩放比例
     * @see #scaleEnd()
     * @see #doubleTap(float, float)
     */
    protected float getMaxScale() {
        return MAX_SCALE;
    }

    /**
     * 计算�?�击之�?�图片接下�?�应该被缩放的比例
     * <p>
     * 如果值大于getMaxScale或者�?于fit center尺寸，则实际使用�?�边界值.
     * 通过覆盖此方法�?�以定制�?�?�的图片被�?�击时使用�?�?�的放大策略.
     *
     * @param innerScale 当�?内部矩阵的缩放值
     * @param outerScale 当�?外部矩阵的缩放值
     * @return 接下�?�的缩放比例
     * @see #doubleTap(float, float)
     * @see #getMaxScale()
     */
    protected float calculateNextScale(float innerScale, float outerScale) {
        float currentScale = innerScale * outerScale;
        if (currentScale < MAX_SCALE) {
            return MAX_SCALE;
        } else {
            return innerScale;
        }
    }

    ////////////////////////////////�?始化////////////////////////////////

    public PinchImageView(Context context) {
        super(context);
        initView();
    }

    public PinchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PinchImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        //强制设置图片scaleType为matrix
        super.setScaleType(ScaleType.MATRIX);
    }

    //�?�?许设置scaleType，�?�能用内部设置的matrix
    @Override
    public void setScaleType(ScaleType scaleType) {
    }

    ////////////////////////////////绘制////////////////////////////////

    @Override
    protected void onDraw(Canvas canvas) {
        //在绘制�?设置�?��?�矩阵
        if (isReady()) {
            Matrix matrix = MathUtils.matrixTake();
            setImageMatrix(getCurrentImageMatrix(matrix));
            MathUtils.matrixGiven(matrix);
        }
        //对图�?�?��?�罩处�?�
        if (mMask != null) {
            canvas.save();
            canvas.clipRect(mMask);
            super.onDraw(canvas);
            canvas.restore();
        } else {
            super.onDraw(canvas);
        }
    }

    ////////////////////////////////有效性判断////////////////////////////////

    /**
     * 判断当�?情况是�?�能执行手势相关计算
     * <p>
     * 包括:是�?�有图片,图片是�?�有尺寸,控件是�?�有尺寸.
     *
     * @return 是�?�能执行手势相关计算
     */
    private boolean isReady() {
        return getDrawable() != null && getDrawable().getIntrinsicWidth() > 0 && getDrawable().getIntrinsicHeight() > 0
                && getWidth() > 0 && getHeight() > 0;
    }

    ////////////////////////////////mask动画处�?�////////////////////////////////

    /**
     * mask修改的动画
     * <p>
     * 和图片的动画相互独立.
     *
     * @see #zoomMaskTo(RectF, long)
     */
    private MaskAnimator mMaskAnimator;

    /**
     * mask�?��?�动画
     * <p>
     * 将mask从一个rect动画到�?�外一个rect
     */
    private class MaskAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {

        /**
         * 开始mask
         */
        private float[] mStart = new float[4];

        /**
         * 结�?�mask
         */
        private float[] mEnd = new float[4];

        /**
         * 中间结果mask
         */
        private float[] mResult = new float[4];

        /**
         * 创建mask�?��?�动画
         *
         * @param start    动画起始状�?
         * @param end      动画终点状�?
         * @param duration 动画�?续时间
         */
        public MaskAnimator(RectF start, RectF end, long duration) {
            super();
            setFloatValues(0, 1f);
            setDuration(duration);
            addUpdateListener(this);
            //将起点终点拷�?到数组方便计算
            mStart[0] = start.left;
            mStart[1] = start.top;
            mStart[2] = start.right;
            mStart[3] = start.bottom;
            mEnd[0] = end.left;
            mEnd[1] = end.top;
            mEnd[2] = end.right;
            mEnd[3] = end.bottom;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            //获�?�动画进度,0-1范围
            float value = (Float) animation.getAnimatedValue();
            //根�?�进度对起点终点之间�?��?�值
            for (int i = 0; i < 4; i++) {
                mResult[i] = mStart[i] + (mEnd[i] - mStart[i]) * value;
            }
            //期间mask有�?�能被置空了,所以判断一下
            if (mMask == null) {
                mMask = new RectF();
            }
            //设置新的mask并绘制
            mMask.set(mResult[0], mResult[1], mResult[2], mResult[3]);
            invalidate();
        }
    }

    ////////////////////////////////手势动画处�?�////////////////////////////////

    /**
     * 在�?�指模�?下:
     * 记录上一次手指的�?置,用于计算新的�?置和上一次�?置的差值.
     * <p>
     * �?�指模�?下:
     * 记录两个手指的中点,作为和mScaleCenter绑定的点.
     * 这个绑定�?�以�?�?mScaleCenter无论如何都会跟�?这个中点.
     *
     * @see #mScaleCenter
     * @see #scale(PointF, float, float, PointF)
     * @see #scaleEnd()
     */
    private PointF mLastMovePoint = new PointF();

    /**
     * 缩放模�?下图片的缩放中点.
     * <p>
     * 为其指代的点�?过innerMatrix�?��?�之�?�的值.
     * 其指代的点在手势过程中始终跟�?mLastMovePoint.
     * 通过�?�指缩放时,其为缩放中心点.
     *
     * @see #saveScaleContext(float, float, float, float)
     * @see #mLastMovePoint
     * @see #scale(PointF, float, float, PointF)
     */
    private PointF mScaleCenter = new PointF();

    /**
     * 缩放模�?下的基础缩放比例
     * <p>
     * 为外层缩放值除以开始缩放时两指�?离.
     * 其值乘上最新的两指之间�?离为最新的图片缩放比例.
     *
     * @see #saveScaleContext(float, float, float, float)
     * @see #scale(PointF, float, float, PointF)
     */
    private float mScaleBase = 0;

    /**
     * 图片缩放动画
     * <p>
     * 缩放模�?把图片的�?置大�?超出�?制之�?�触�?�.
     * �?�击图片放大或缩�?时触�?�.
     * 手动调用outerMatrixTo触�?�.
     *
     * @see #scaleEnd()
     * @see #doubleTap(float, float)
     * @see #outerMatrixTo(Matrix, long)
     */
    private ScaleAnimator mScaleAnimator;

    /**
     * 滑动产生的惯性动画
     *
     * @see #fling(float, float)
     */
    private FlingAnimator mFlingAnimator;

    /**
     * 常用手势处�?�
     * <p>
     * 在onTouchEvent末尾被执行.
     */
    private GestureDetector mGestureDetector = new GestureDetector(PinchImageView.this.getContext(), new GestureDetector.SimpleOnGestureListener() {

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //�?�有在�?�指模�?结�?�之�?��?�?许执行fling
            if (mPinchMode == PINCH_MODE_FREE && !(mScaleAnimator != null && mScaleAnimator.isRunning())) {
                fling(velocityX, velocityY);
            }
            return true;
        }

        public void onLongPress(MotionEvent e) {
            //触�?�长按
            if (mOnLongClickListener != null) {
                mOnLongClickListener.onLongClick(PinchImageView.this);
            }
        }

        public boolean onDoubleTap(MotionEvent e) {
            //当手指快速第二次按下触�?�,此时必须是�?�指模�?�?�?许执行doubleTap
            if (mPinchMode == PINCH_MODE_SCROLL && !(mScaleAnimator != null && mScaleAnimator.isRunning())) {
                doubleTap(e.getX(), e.getY());
            }
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent e) {
            //触�?�点击
            if (mOnClickListener != null) {
                mOnClickListener.onClick(PinchImageView.this);
            }
            return true;
        }
    });

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        //最�?�一个点抬起或者�?�消，结�?�所有模�?
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            //如果之�?是缩放模�?,还需�?触�?�一下缩放结�?�动画
            if (mPinchMode == PINCH_MODE_SCALE) {
                scaleEnd();
            }
            mPinchMode = PINCH_MODE_FREE;
        } else if (action == MotionEvent.ACTION_POINTER_UP) {
            //多个手指情况下抬起一个手指,此时需�?是缩放模�?�?触�?�
            if (mPinchMode == PINCH_MODE_SCALE) {
                //抬起的点如果大于2，那么缩放模�?还有效，但是有�?�能�?始点�?�了，�?新测�?�?始点
                if (event.getPointerCount() > 2) {
                    //如果还没结�?�缩放模�?，但是第一个点抬起了，那么让第二个点和第三个点作为缩放控制点
                    if (event.getAction() >> 8 == 0) {
                        saveScaleContext(event.getX(1), event.getY(1), event.getX(2), event.getY(2));
                        //如果还没结�?�缩放模�?，但是第二个点抬起了，那么让第一个点和第三个点作为缩放控制点
                    } else if (event.getAction() >> 8 == 1) {
                        saveScaleContext(event.getX(0), event.getY(0), event.getX(2), event.getY(2));
                    }
                }
                //如果抬起的点等于2,那么此时�?�剩下一个点,也�?�?许进入�?�指模�?,因为此时�?�能图片没有在正确的�?置上
            }
            //第一个点按下，开�?�滚动模�?，记录开始滚动的点
        } else if (action == MotionEvent.ACTION_DOWN) {
            //在矩阵动画过程中�?�?许�?�动滚动模�?
            if (!(mScaleAnimator != null && mScaleAnimator.isRunning())) {
                //�?�止所有动画
                cancelAllAnimator();
                //切�?�到滚动模�?
                mPinchMode = PINCH_MODE_SCROLL;
                //�?存触�?�点用于move计算差值
                mLastMovePoint.set(event.getX(), event.getY());
            }
            //�?�第一个点按下，关闭滚动模�?，开�?�缩放模�?，记录缩放模�?的一些�?始数�?�
        } else if (action == MotionEvent.ACTION_POINTER_DOWN) {
            //�?�止所有动画
            cancelAllAnimator();
            //切�?�到缩放模�?
            mPinchMode = PINCH_MODE_SCALE;
            //�?存缩放的两个手指
            saveScaleContext(event.getX(0), event.getY(0), event.getX(1), event.getY(1));
        } else if (action == MotionEvent.ACTION_MOVE) {
            if (!(mScaleAnimator != null && mScaleAnimator.isRunning())) {
                //在滚动模�?下移动
                if (mPinchMode == PINCH_MODE_SCROLL) {
                    //�?次移动产生一个差值累积到图片�?置上
                    scrollBy(event.getX() - mLastMovePoint.x, event.getY() - mLastMovePoint.y);
                    //记录新的移动点
                    mLastMovePoint.set(event.getX(), event.getY());
                    //在缩放模�?下移动
                } else if (mPinchMode == PINCH_MODE_SCALE && event.getPointerCount() > 1) {
                    //两个缩放点间的�?离
                    float distance = MathUtils.getDistance(event.getX(0), event.getY(0), event.getX(1), event.getY(1));
                    //�?存缩放点中点
                    float[] lineCenter = MathUtils.getCenterPoint(event.getX(0), event.getY(0), event.getX(1), event.getY(1));
                    mLastMovePoint.set(lineCenter[0], lineCenter[1]);
                    //处�?�缩放
                    scale(mScaleCenter, mScaleBase, distance, mLastMovePoint);
                }
            }
        }
        //无论如何都处�?��?��?外部手势
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    /**
     * 让图片移动一段�?离
     * <p>
     * �?能移动超过�?�移动范围,超过了就到�?�移动范围边界为止.
     *
     * @param xDiff 移动�?离
     * @param yDiff 移动�?离
     * @return 是�?�改�?�了�?置
     */
    private boolean scrollBy(float xDiff, float yDiff) {
        if (!isReady()) {
            return false;
        }
        //原图方框
        RectF bound = MathUtils.rectFTake();
        getImageBound(bound);
        //控件大�?
        float displayWidth = getWidth();
        float displayHeight = getHeight();
        //如果当�?图片宽度�?于控件宽度，则�?能移动
        if (bound.right - bound.left < displayWidth) {
            xDiff = 0;
            //如果图片左边在移动�?�超出控件左边
        } else if (bound.left + xDiff > 0) {
            //如果在移动之�?是没超出的，计算应该移动的�?离
            if (bound.left < 0) {
                xDiff = -bound.left;
                //�?�则无法移动
            } else {
                xDiff = 0;
            }
            //如果图片�?�边在移动�?�超出控件�?�边
        } else if (bound.right + xDiff < displayWidth) {
            //如果在移动之�?是没超出的，计算应该移动的�?离
            if (bound.right > displayWidth) {
                xDiff = displayWidth - bound.right;
                //�?�则无法移动
            } else {
                xDiff = 0;
            }
        }
        //以下�?��?�
        if (bound.bottom - bound.top < displayHeight) {
            yDiff = 0;
        } else if (bound.top + yDiff > 0) {
            if (bound.top < 0) {
                yDiff = -bound.top;
            } else {
                yDiff = 0;
            }
        } else if (bound.bottom + yDiff < displayHeight) {
            if (bound.bottom > displayHeight) {
                yDiff = displayHeight - bound.bottom;
            } else {
                yDiff = 0;
            }
        }
        MathUtils.rectFGiven(bound);
        //应用移动�?��?�
        mOuterMatrix.postTranslate(xDiff, yDiff);
        dispatchOuterMatrixChanged();
        //触�?��?绘
        invalidate();
        //检查是�?�有�?�化
        if (xDiff != 0 || yDiff != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 记录缩放�?的一些信�?�
     * <p>
     * �?存基础缩放值.
     * �?存图片缩放中点.
     *
     * @param x1 缩放第一个手指
     * @param y1 缩放第一个手指
     * @param x2 缩放第二个手指
     * @param y2 缩放第二个手指
     */
    private void saveScaleContext(float x1, float y1, float x2, float y2) {
        //记录基础缩放值,其中图片缩放比例按照x方�?��?�计算
        //�?�论上图片应该是等比的,x和y方�?�比例相�?�
        //但是有�?�能外部设定了�?规范的值.
        //但是�?�续的scale�?作会将xy�?等的缩放值纠正,改�?和x方�?�相�?�
        mScaleBase = MathUtils.getMatrixScale(mOuterMatrix)[0] / MathUtils.getDistance(x1, y1, x2, y2);
        //两手指的中点在�?幕上�?�在了图片的�?个点上,图片上的这个点在�?过总矩阵�?��?��?�和手指中点相�?�
        //现在我们需�?得到图片上这个点在图片是fit center状�?下在�?幕上的�?置
        //因为�?�续的计算都是基于图片是fit center状�?下进行�?��?�
        //所以需�?把两手指中点除以外层�?��?�矩阵得到mScaleCenter
        float[] center = MathUtils.inverseMatrixPoint(MathUtils.getCenterPoint(x1, y1, x2, y2), mOuterMatrix);
        mScaleCenter.set(center[0], center[1]);
    }

    /**
     * 对图片按照一些手势信�?�进行缩放
     *
     * @param scaleCenter mScaleCenter
     * @param scaleBase   mScaleBase
     * @param distance    手指两点之间�?离
     * @param lineCenter  手指两点之间中点
     * @see #mScaleCenter
     * @see #mScaleBase
     */
    private void scale(PointF scaleCenter, float scaleBase, float distance, PointF lineCenter) {
        if (!isReady()) {
            return;
        }
        //计算图片从fit center状�?到目标状�?的缩放比例
        float scale = scaleBase * distance;
        Matrix matrix = MathUtils.matrixTake();
        //按照图片缩放中心缩放，并且让缩放中心在缩放点中点上
        matrix.postScale(scale, scale, scaleCenter.x, scaleCenter.y);
        //让图片的缩放中点跟�?手指缩放中点
        matrix.postTranslate(lineCenter.x - scaleCenter.x, lineCenter.y - scaleCenter.y);
        //应用�?��?�
        mOuterMatrix.set(matrix);
        MathUtils.matrixGiven(matrix);
        dispatchOuterMatrixChanged();
        //�?绘
        invalidate();
    }

    /**
     * �?�击�?�放大或者缩�?
     * <p>
     * 将图片缩放比例缩放到nextScale指定的值.
     * 但nextScale值�?能大于最大缩放值�?能�?于fit center情况下的缩放值.
     * 将�?�击的点尽�?移动到控件中心.
     *
     * @param x �?�击的点
     * @param y �?�击的点
     * @see #calculateNextScale(float, float)
     * @see #getMaxScale()
     */
    private void doubleTap(float x, float y) {
        if (!isReady()) {
            return;
        }
        //获�?�第一层�?��?�矩阵
        Matrix innerMatrix = MathUtils.matrixTake();
        getInnerMatrix(innerMatrix);
        //当�?总的缩放比例
        float innerScale = MathUtils.getMatrixScale(innerMatrix)[0];
        float outerScale = MathUtils.getMatrixScale(mOuterMatrix)[0];
        float currentScale = innerScale * outerScale;
        //控件大�?
        float displayWidth = getWidth();
        float displayHeight = getHeight();
        //最大放大大�?
        float maxScale = getMaxScale();
        //接下�?��?放大的大�?
        float nextScale = calculateNextScale(innerScale, outerScale);
        //如果接下�?�放大大于最大值或者�?于fit center值，则�?�边界
        if (nextScale > maxScale) {
            nextScale = maxScale;
        }
        if (nextScale < innerScale) {
            nextScale = innerScale;
        }
        //开始计算缩放动画的结果矩阵
        Matrix animEnd = MathUtils.matrixTake(mOuterMatrix);
        //计算还需缩放的�?数
        animEnd.postScale(nextScale / currentScale, nextScale / currentScale, x, y);
        //将放大点移动到控件中心
        animEnd.postTranslate(displayWidth / 2f - x, displayHeight / 2f - y);
        //得到放大之�?�的图片方框
        Matrix testMatrix = MathUtils.matrixTake(innerMatrix);
        testMatrix.postConcat(animEnd);
        RectF testBound = MathUtils.rectFTake(0, 0, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
        testMatrix.mapRect(testBound);
        //修正�?置
        float postX = 0;
        float postY = 0;
        if (testBound.right - testBound.left < displayWidth) {
            postX = displayWidth / 2f - (testBound.right + testBound.left) / 2f;
        } else if (testBound.left > 0) {
            postX = -testBound.left;
        } else if (testBound.right < displayWidth) {
            postX = displayWidth - testBound.right;
        }
        if (testBound.bottom - testBound.top < displayHeight) {
            postY = displayHeight / 2f - (testBound.bottom + testBound.top) / 2f;
        } else if (testBound.top > 0) {
            postY = -testBound.top;
        } else if (testBound.bottom < displayHeight) {
            postY = displayHeight - testBound.bottom;
        }
        //应用修正�?置
        animEnd.postTranslate(postX, postY);
        //清�?�当�?�?�能正在执行的动画
        cancelAllAnimator();
        //�?�动矩阵动画
        mScaleAnimator = new ScaleAnimator(mOuterMatrix, animEnd);
        mScaleAnimator.start();
        //清�?�临时�?��?
        MathUtils.rectFGiven(testBound);
        MathUtils.matrixGiven(testMatrix);
        MathUtils.matrixGiven(animEnd);
        MathUtils.matrixGiven(innerMatrix);
    }

    /**
     * 当缩放�?作结�?�动画
     * <p>
     * 如果图片超过边界,找到最近的�?置动画�?��?.
     * 如果图片缩放尺寸超过最大值或者最�?值,找到最近的值动画�?��?.
     */
    private void scaleEnd() {
        if (!isReady()) {
            return;
        }
        //是�?�修正了�?置
        boolean change = false;
        //获�?�图片整体的�?��?�矩阵
        Matrix currentMatrix = MathUtils.matrixTake();
        getCurrentImageMatrix(currentMatrix);
        //整体缩放比例
        float currentScale = MathUtils.getMatrixScale(currentMatrix)[0];
        //第二层缩放比例
        float outerScale = MathUtils.getMatrixScale(mOuterMatrix)[0];
        //控件大�?
        float displayWidth = getWidth();
        float displayHeight = getHeight();
        //最大缩放比例
        float maxScale = getMaxScale();
        //比例修正
        float scalePost = 1f;
        //�?置修正
        float postX = 0;
        float postY = 0;
        //如果整体缩放比例大于最大比例，进行缩放修正
        if (currentScale > maxScale) {
            scalePost = maxScale / currentScale;
        }
        //如果缩放修正�?�整体导致第二层缩放�?于1（就是图片比fit center状�?还�?），�?新修正缩放
        if (outerScale * scalePost < 1f) {
            scalePost = 1f / outerScale;
        }
        //如果缩放修正�?为1，说明进行了修正
        if (scalePost != 1f) {
            change = true;
        }
        //�?试根�?�缩放点进行缩放修正
        Matrix testMatrix = MathUtils.matrixTake(currentMatrix);
        testMatrix.postScale(scalePost, scalePost, mLastMovePoint.x, mLastMovePoint.y);
        RectF testBound = MathUtils.rectFTake(0, 0, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
        //获�?�缩放修正�?�的图片方框
        testMatrix.mapRect(testBound);
        //检测缩放修正�?��?置有无超出，如果超出进行�?置修正
        if (testBound.right - testBound.left < displayWidth) {
            postX = displayWidth / 2f - (testBound.right + testBound.left) / 2f;
        } else if (testBound.left > 0) {
            postX = -testBound.left;
        } else if (testBound.right < displayWidth) {
            postX = displayWidth - testBound.right;
        }
        if (testBound.bottom - testBound.top < displayHeight) {
            postY = displayHeight / 2f - (testBound.bottom + testBound.top) / 2f;
        } else if (testBound.top > 0) {
            postY = -testBound.top;
        } else if (testBound.bottom < displayHeight) {
            postY = displayHeight - testBound.bottom;
        }
        //如果�?置修正�?为0，说明进行了修正
        if (postX != 0 || postY != 0) {
            change = true;
        }
        //�?�有有执行修正�?执行动画
        if (change) {
            //计算结�?�矩阵
            Matrix animEnd = MathUtils.matrixTake(mOuterMatrix);
            animEnd.postScale(scalePost, scalePost, mLastMovePoint.x, mLastMovePoint.y);
            animEnd.postTranslate(postX, postY);
            //清�?�当�?�?�能正在执行的动画
            cancelAllAnimator();
            //�?�动矩阵动画
            mScaleAnimator = new ScaleAnimator(mOuterMatrix, animEnd);
            mScaleAnimator.start();
            //清�?�临时�?��?
            MathUtils.matrixGiven(animEnd);
        }
        //清�?�临时�?��?
        MathUtils.rectFGiven(testBound);
        MathUtils.matrixGiven(testMatrix);
        MathUtils.matrixGiven(currentMatrix);
    }

    /**
     * 执行惯性动画
     * <p>
     * 动画在�?�到�?能移动就�?�止.
     * 动画速度衰�?到很�?就�?�止.
     * <p>
     * 其中�?�数速度�?��?为 �?素/秒
     *
     * @param vx x方�?�速度
     * @param vy y方�?�速度
     */
    private void fling(float vx, float vy) {
        if (!isReady()) {
            return;
        }
        //清�?�当�?�?�能正在执行的动画
        cancelAllAnimator();
        //创建惯性动画
        //FlingAnimator�?��?为 �?素/帧,一秒60帧
        mFlingAnimator = new FlingAnimator(vx / 60f, vy / 60f);
        mFlingAnimator.start();
    }

    /**
     * �?�止所有手势动画
     */
    private void cancelAllAnimator() {
        if (mScaleAnimator != null) {
            mScaleAnimator.cancel();
            mScaleAnimator = null;
        }
        if (mFlingAnimator != null) {
            mFlingAnimator.cancel();
            mFlingAnimator = null;
        }
    }

    /**
     * 惯性动画
     * <p>
     * 速度�?�?衰�?,�?帧速度衰�?为原�?�的FLING_DAMPING_FACTOR,当速度衰�?到�?于1时�?�止.
     * 当图片�?能移动时,动画�?�止.
     */
    private class FlingAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {

        /**
         * 速度�?��?
         */
        private float[] mVector;

        /**
         * 创建惯性动画
         * <p>
         * �?�数�?��?为 �?素/帧
         *
         * @param vectorX 速度�?��?
         * @param vectorY 速度�?��?
         */
        public FlingAnimator(float vectorX, float vectorY) {
            super();
            setFloatValues(0, 1f);
            setDuration(1000000);
            addUpdateListener(this);
            mVector = new float[]{vectorX, vectorY};
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            //移动图�?并给出结果
            boolean result = scrollBy(mVector[0], mVector[1]);
            //衰�?速度
            mVector[0] *= FLING_DAMPING_FACTOR;
            mVector[1] *= FLING_DAMPING_FACTOR;
            //速度太�?或者�?能移动了就结�?�
            if (!result || MathUtils.getDistance(0, 0, mVector[0], mVector[1]) < 1f) {
                animation.cancel();
            }
        }
    }

    /**
     * 缩放动画
     * <p>
     * 在给定时间内从一个矩阵的�?�化�?�?动画到�?�一个矩阵的�?�化
     */
    private class ScaleAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {

        /**
         * 开始矩阵
         */
        private float[] mStart = new float[9];

        /**
         * 结�?�矩阵
         */
        private float[] mEnd = new float[9];

        /**
         * 中间结果矩阵
         */
        private float[] mResult = new float[9];

        /**
         * 构建一个缩放动画
         * <p>
         * 从一个矩阵�?��?�到�?�外一个矩阵
         *
         * @param start 开始矩阵
         * @param end   结�?�矩阵
         */
        public ScaleAnimator(Matrix start, Matrix end) {
            this(start, end, SCALE_ANIMATOR_DURATION);
        }

        /**
         * 构建一个缩放动画
         * <p>
         * 从一个矩阵�?��?�到�?�外一个矩阵
         *
         * @param start    开始矩阵
         * @param end      结�?�矩阵
         * @param duration 动画时间
         */
        public ScaleAnimator(Matrix start, Matrix end, long duration) {
            super();
            setFloatValues(0, 1f);
            setDuration(duration);
            addUpdateListener(this);
            start.getValues(mStart);
            end.getValues(mEnd);
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            //获�?�动画进度
            float value = (Float) animation.getAnimatedValue();
            //根�?�动画进度计算矩阵中间�?�值
            for (int i = 0; i < 9; i++) {
                mResult[i] = mStart[i] + (mEnd[i] - mStart[i]) * value;
            }
            //设置矩阵并�?绘
            mOuterMatrix.setValues(mResult);
            dispatchOuterMatrixChanged();
            invalidate();
        }
    }


    ////////////////////////////////防止内存抖动�?用对象////////////////////////////////

    /**
     * 对象池
     * <p>
     * 防止频�?new对象产生内存抖动.
     * 由于对象池最大长度�?制,如果�?�度�?超过对象池容�?,�?然会�?�生抖动.
     * 此时需�?增大对象池容�?,但是会�?�用更多内存.
     *
     * @param <T> 对象池容纳的对象类型
     */
    private static abstract class ObjectsPool<T> {

        /**
         * 对象池的最大容�?
         */
        private int mSize;

        /**
         * 对象池队列
         */
        private Queue<T> mQueue;

        /**
         * 创建一个对象池
         *
         * @param size 对象池最大容�?
         */
        public ObjectsPool(int size) {
            mSize = size;
            mQueue = new LinkedList<T>();
        }

        /**
         * 获�?�一个空闲的对象
         * <p>
         * 如果对象池为空,则对象池自己会new一个返回.
         * 如果对象池内有对象,则�?�一个已存在的返回.
         * take出�?�的对象用完�?记得调用given归还.
         * 如果�?归还,让然会�?�生内存抖动,但�?会引起泄�?.
         *
         * @return �?�用的对象
         * @see #given(Object)
         */
        public T take() {
            //如果池内为空就创建一个
            if (mQueue.size() == 0) {
                return newInstance();
            } else {
                //对象池里有就从顶端拿出�?�一个返回
                return resetInstance(mQueue.poll());
            }
        }

        /**
         * 归还对象池内申请的对象
         * <p>
         * 如果归还的对象数�?超过对象池容�?,那么归还的对象就会被丢弃.
         *
         * @param obj 归还的对象
         * @see #take()
         */
        public void given(T obj) {
            //如果对象池还有空�?�?就归还对象
            if (obj != null && mQueue.size() < mSize) {
                mQueue.offer(obj);
            }
        }

        /**
         * 实例化对象
         *
         * @return 创建的对象
         */
        abstract protected T newInstance();

        /**
         * �?置对象
         * <p>
         * 把对象数�?�清空到就�?刚创建的一样.
         *
         * @param obj 需�?被�?置的对象
         * @return 被�?置之�?�的对象
         */
        abstract protected T resetInstance(T obj);
    }

    /**
     * 矩阵对象池
     */
    private static class MatrixPool extends ObjectsPool<Matrix> {

        public MatrixPool(int size) {
            super(size);
        }

        @Override
        protected Matrix newInstance() {
            return new Matrix();
        }

        @Override
        protected Matrix resetInstance(Matrix obj) {
            obj.reset();
            return obj;
        }
    }

    /**
     * 矩形对象池
     */
    private static class RectFPool extends ObjectsPool<RectF> {

        public RectFPool(int size) {
            super(size);
        }

        @Override
        protected RectF newInstance() {
            return new RectF();
        }

        @Override
        protected RectF resetInstance(RectF obj) {
            obj.setEmpty();
            return obj;
        }
    }


    ////////////////////////////////数学计算工具类////////////////////////////////

    /**
     * 数学计算工具类
     */
    public static class MathUtils {

        /**
         * 矩阵对象池
         */
        private static MatrixPool mMatrixPool = new MatrixPool(16);

        /**
         * 获�?�矩阵对象
         */
        public static Matrix matrixTake() {
            return mMatrixPool.take();
        }

        /**
         * 获�?��?个矩阵的copy
         */
        public static Matrix matrixTake(Matrix matrix) {
            Matrix result = mMatrixPool.take();
            if (matrix != null) {
                result.set(matrix);
            }
            return result;
        }

        /**
         * 归还矩阵对象
         */
        public static void matrixGiven(Matrix matrix) {
            mMatrixPool.given(matrix);
        }

        /**
         * 矩形对象池
         */
        private static RectFPool mRectFPool = new RectFPool(16);

        /**
         * 获�?�矩形对象
         */
        public static RectF rectFTake() {
            return mRectFPool.take();
        }

        /**
         * 按照指定值获�?�矩形对象
         */
        public static RectF rectFTake(float left, float top, float right, float bottom) {
            RectF result = mRectFPool.take();
            result.set(left, top, right, bottom);
            return result;
        }

        /**
         * 获�?��?个矩形的副本
         */
        public static RectF rectFTake(RectF rectF) {
            RectF result = mRectFPool.take();
            if (rectF != null) {
                result.set(rectF);
            }
            return result;
        }

        /**
         * 归还矩形对象
         */
        public static void rectFGiven(RectF rectF) {
            mRectFPool.given(rectF);
        }

        /**
         * 获�?�两点之间�?离
         *
         * @param x1 点1
         * @param y1 点1
         * @param x2 点2
         * @param y2 点2
         * @return �?离
         */
        public static float getDistance(float x1, float y1, float x2, float y2) {
            float x = x1 - x2;
            float y = y1 - y2;
            return (float) Math.sqrt(x * x + y * y);
        }

        /**
         * 获�?�两点的中点
         *
         * @param x1 点1
         * @param y1 点1
         * @param x2 点2
         * @param y2 点2
         * @return float[]{x, y}
         */
        public static float[] getCenterPoint(float x1, float y1, float x2, float y2) {
            return new float[]{(x1 + x2) / 2f, (y1 + y2) / 2f};
        }

        /**
         * 获�?�矩阵的缩放值
         *
         * @param matrix �?计算的矩阵
         * @return float[]{scaleX, scaleY}
         */
        public static float[] getMatrixScale(Matrix matrix) {
            if (matrix != null) {
                float[] value = new float[9];
                matrix.getValues(value);
                return new float[]{value[0], value[4]};
            } else {
                return new float[2];
            }
        }

        /**
         * 计算点除以矩阵的值
         * <p>
         * matrix.mapPoints(unknownPoint) -> point
         * 已知point和matrix,求unknownPoint的值.
         *
         * @param point
         * @param matrix
         * @return unknownPoint
         */
        public static float[] inverseMatrixPoint(float[] point, Matrix matrix) {
            if (point != null && matrix != null) {
                float[] dst = new float[2];
                //计算matrix的逆矩阵
                Matrix inverse = matrixTake();
                matrix.invert(inverse);
                //用逆矩阵�?��?�point到dst,dst就是结果
                inverse.mapPoints(dst, point);
                //清除临时�?��?
                matrixGiven(inverse);
                return dst;
            } else {
                return new float[2];
            }
        }

        /**
         * 计算两个矩形之间的�?��?�矩阵
         * <p>
         * unknownMatrix.mapRect(to, from)
         * 已知from矩形和to矩形,求unknownMatrix
         *
         * @param from
         * @param to
         * @param result unknownMatrix
         */
        public static void calculateRectTranslateMatrix(RectF from, RectF to, Matrix result) {
            if (from == null || to == null || result == null) {
                return;
            }
            if (from.width() == 0 || from.height() == 0) {
                return;
            }
            result.reset();
            result.postTranslate(-from.left, -from.top);
            result.postScale(to.width() / from.width(), to.height() / from.height());
            result.postTranslate(to.left, to.top);
        }

        /**
         * 计算图片在�?个ImageView中的显示矩形
         *
         * @param container ImageView的Rect
         * @param srcWidth  图片的宽度
         * @param srcHeight 图片的高度
         * @param scaleType 图片在ImageView中的ScaleType
         * @param result    图片应该在ImageView中展示的矩形
         */
        public static void calculateScaledRectInContainer(RectF container, float srcWidth, float srcHeight, ScaleType scaleType, RectF result) {
            if (container == null || result == null) {
                return;
            }
            if (srcWidth == 0 || srcHeight == 0) {
                return;
            }
            //默认scaleType为fit center
            if (scaleType == null) {
                scaleType = ScaleType.FIT_CENTER;
            }
            result.setEmpty();
            if (ScaleType.FIT_XY.equals(scaleType)) {
                result.set(container);
            } else if (ScaleType.CENTER.equals(scaleType)) {
                Matrix matrix = matrixTake();
                RectF rect = rectFTake(0, 0, srcWidth, srcHeight);
                matrix.setTranslate((container.width() - srcWidth) * 0.5f, (container.height() - srcHeight) * 0.5f);
                matrix.mapRect(result, rect);
                rectFGiven(rect);
                matrixGiven(matrix);
                result.left += container.left;
                result.right += container.left;
                result.top += container.top;
                result.bottom += container.top;
            } else if (ScaleType.CENTER_CROP.equals(scaleType)) {
                Matrix matrix = matrixTake();
                RectF rect = rectFTake(0, 0, srcWidth, srcHeight);
                float scale;
                float dx = 0;
                float dy = 0;
                if (srcWidth * container.height() > container.width() * srcHeight) {
                    scale = container.height() / srcHeight;
                    dx = (container.width() - srcWidth * scale) * 0.5f;
                } else {
                    scale = container.width() / srcWidth;
                    dy = (container.height() - srcHeight * scale) * 0.5f;
                }
                matrix.setScale(scale, scale);
                matrix.postTranslate(dx, dy);
                matrix.mapRect(result, rect);
                rectFGiven(rect);
                matrixGiven(matrix);
                result.left += container.left;
                result.right += container.left;
                result.top += container.top;
                result.bottom += container.top;
            } else if (ScaleType.CENTER_INSIDE.equals(scaleType)) {
                Matrix matrix = matrixTake();
                RectF rect = rectFTake(0, 0, srcWidth, srcHeight);
                float scale;
                float dx;
                float dy;
                if (srcWidth <= container.width() && srcHeight <= container.height()) {
                    scale = 1f;
                } else {
                    scale = Math.min(container.width() / srcWidth, container.height() / srcHeight);
                }
                dx = (container.width() - srcWidth * scale) * 0.5f;
                dy = (container.height() - srcHeight * scale) * 0.5f;
                matrix.setScale(scale, scale);
                matrix.postTranslate(dx, dy);
                matrix.mapRect(result, rect);
                rectFGiven(rect);
                matrixGiven(matrix);
                result.left += container.left;
                result.right += container.left;
                result.top += container.top;
                result.bottom += container.top;
            } else if (ScaleType.FIT_CENTER.equals(scaleType)) {
                Matrix matrix = matrixTake();
                RectF rect = rectFTake(0, 0, srcWidth, srcHeight);
                RectF tempSrc = rectFTake(0, 0, srcWidth, srcHeight);
                RectF tempDst = rectFTake(0, 0, container.width(), container.height());
                matrix.setRectToRect(tempSrc, tempDst, Matrix.ScaleToFit.CENTER);
                matrix.mapRect(result, rect);
                rectFGiven(tempDst);
                rectFGiven(tempSrc);
                rectFGiven(rect);
                matrixGiven(matrix);
                result.left += container.left;
                result.right += container.left;
                result.top += container.top;
                result.bottom += container.top;
            } else if (ScaleType.FIT_START.equals(scaleType)) {
                Matrix matrix = matrixTake();
                RectF rect = rectFTake(0, 0, srcWidth, srcHeight);
                RectF tempSrc = rectFTake(0, 0, srcWidth, srcHeight);
                RectF tempDst = rectFTake(0, 0, container.width(), container.height());
                matrix.setRectToRect(tempSrc, tempDst, Matrix.ScaleToFit.START);
                matrix.mapRect(result, rect);
                rectFGiven(tempDst);
                rectFGiven(tempSrc);
                rectFGiven(rect);
                matrixGiven(matrix);
                result.left += container.left;
                result.right += container.left;
                result.top += container.top;
                result.bottom += container.top;
            } else if (ScaleType.FIT_END.equals(scaleType)) {
                Matrix matrix = matrixTake();
                RectF rect = rectFTake(0, 0, srcWidth, srcHeight);
                RectF tempSrc = rectFTake(0, 0, srcWidth, srcHeight);
                RectF tempDst = rectFTake(0, 0, container.width(), container.height());
                matrix.setRectToRect(tempSrc, tempDst, Matrix.ScaleToFit.END);
                matrix.mapRect(result, rect);
                rectFGiven(tempDst);
                rectFGiven(tempSrc);
                rectFGiven(rect);
                matrixGiven(matrix);
                result.left += container.left;
                result.right += container.left;
                result.top += container.top;
                result.bottom += container.top;
            } else {
                result.set(container);
            }
        }
    }
}
