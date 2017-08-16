package info.kimjihyok.library.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class ZoomableLayout extends RelativeLayout {
  private static final String TAG = "ZoomableLayout";
  // Constants for MAX, MIN zoom
  private static final int MAX_ZOOM = 2;
  private static final int MIN_ZOOM = 1;

  private static float MAX_TRANS_X;
  private static float MAX_TRANS_Y;
  private static float MIN_TRANS_X;
  private static float MIN_TRANS_Y;

  // these matrices will be used to zoom image
  private Matrix matrix = new Matrix();
  private Matrix matrixInverse = new Matrix();
  private Matrix savedMatrix = new Matrix();

  // we can be in one of these 2 states
  private static final int NONE = 0;
  private static final int ZOOM = 1;
//  private static final int DRAG = 2;

  private int mode = NONE;


  // remember some things for zooming
  private PointF start = new PointF();
  private PointF mid = new PointF();

  private float oldDist = 1f;
  private float[] lastEvent = null;
  private float[] mDispatchTouchEventWorkingArray = new float[2];
  private float[] mOnTouchEventWorkingArray = new float[2];

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return true;
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    mDispatchTouchEventWorkingArray[0] = ev.getX();
    mDispatchTouchEventWorkingArray[1] = ev.getY();
    mDispatchTouchEventWorkingArray = screenPointsToScaledPoints(mDispatchTouchEventWorkingArray);
    ev.setLocation(mDispatchTouchEventWorkingArray[0],
        mDispatchTouchEventWorkingArray[1]);
    return super.dispatchTouchEvent(ev);
  }

  private float[] scaledPointsToScreenPoints(float[] a) {
    matrix.mapPoints(a);
    return a;
  }

  private float[] screenPointsToScaledPoints(float[] a) {
    matrixInverse.mapPoints(a);
    return a;
  }

  public ZoomableLayout(Context context) {
    this(context, null);
  }

  public ZoomableLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ZoomableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  /**
   * Determine the space between the first two fingers
   */
  private float spacing(MotionEvent event) {
    try {
      float x = event.getX(0) - event.getX(1);
      float y = event.getY(0) - event.getY(1);
      return (float) Math.sqrt(x * x + y * y);
    } catch (Exception e) {
      // It is a drag, do nothing
      e.printStackTrace();
      return 0;
    }
  }

  /**
   * Calculate the mid point of the first two fingers
   */
  private void midPoint(PointF point, MotionEvent event) {
    float x = event.getX(0) + event.getX(1);
    float y = event.getY(0) + event.getY(1);
    point.set(x / 2, y / 2);
  }


  private void init(Context context) {

  }


  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(i);
      if (child.getVisibility() != GONE) {
        child.layout(l, t, l + child.getMeasuredWidth(), t + child.getMeasuredHeight());
      }
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    float[] values = new float[9];
    matrix.getValues(values);

    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      View child = getChildAt(i);
      if (child.getVisibility() != GONE) {
        measureChild(child, widthMeasureSpec, heightMeasureSpec);
      }
    }

  }

  @Override
  protected void dispatchDraw(Canvas canvas) {
    // factor 2: max matrix: [scale, 0, scale * width - width], [0, scale, scale * height - height], [0, 0, 1.0]
    // factor 2: min matrix: [scale, 0, (scale * width - width) * -1], [0, scale, (scale * height - height) * -1], [0, 0, 1.0]
    float[] values = new float[9];
    matrix.getValues(values);


    // set max trans x, y values according to scale value
    // max matrix: [scale, 0, scale * width - width], [0, scale, scale * height - height], [0, 0, 1.0]
    // min matrix: [scale, 0, (scale * width - width) * -1], [0, scale, (scale * height - height) * -1], [0, 0, 1.0]
    if (values[Matrix.MSCALE_X] >= MAX_ZOOM) {
      values[Matrix.MSCALE_X] = 1f;
      values[Matrix.MSCALE_Y] = 1f;
      values[Matrix.MPERSP_2] = 1f;

      values[Matrix.MTRANS_X] = 0f;
      values[Matrix.MTRANS_Y] = 0f;

      values[Matrix.MSKEW_X] = 0f;
      values[Matrix.MSKEW_Y] = 0f;

      values[Matrix.MPERSP_0] = 0f;
      values[Matrix.MPERSP_1] = 0f;
    } else {
      MAX_TRANS_X = 0;
      MAX_TRANS_Y = 0;
      MIN_TRANS_X = ((values[Matrix.MSCALE_X] * getWidth()) - getWidth()) * -1;
      MIN_TRANS_Y = ((values[Matrix.MSCALE_X] * getChildAt(0).getHeight()) - getChildAt(0).getHeight()) * -1;

//      Log.d("dispatchDraw", "MIN MAX: " + "[" + MIN_TRANS_X + ", " + MAX_TRANS_X + "] [" + MIN_TRANS_Y + ", " + MAX_TRANS_Y + "]");
//      Log.d("dispatchDraw", matrix.toShortString());

      // limit transformation matrix value
      if (values[Matrix.MTRANS_X] > MAX_TRANS_X) {
        values[Matrix.MTRANS_X] = MAX_TRANS_X;
      }
      if (values[Matrix.MTRANS_Y] > MAX_TRANS_Y) {
        values[Matrix.MTRANS_Y] = MAX_TRANS_Y;
      }
      if (values[Matrix.MTRANS_X] < MIN_TRANS_X) {
        values[Matrix.MTRANS_X] = MIN_TRANS_X;
      }
      if (values[Matrix.MTRANS_Y] < MIN_TRANS_Y) {
        values[Matrix.MTRANS_Y] = MIN_TRANS_Y;
      }
    }

    matrix.setValues(values);

    canvas.save();
    canvas.setMatrix(matrix);
    super.dispatchDraw(canvas);
    canvas.restore();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    // handle touch events here
    mOnTouchEventWorkingArray[0] = event.getX();
    mOnTouchEventWorkingArray[1] = event.getY();

    mOnTouchEventWorkingArray = scaledPointsToScreenPoints(mOnTouchEventWorkingArray);

    event.setLocation(mOnTouchEventWorkingArray[0], mOnTouchEventWorkingArray[1]);

    switch (event.getAction() & MotionEvent.ACTION_MASK) {
      case MotionEvent.ACTION_DOWN:
        savedMatrix.set(matrix);
        start.set(event.getX(), event.getY());
        lastEvent = null;
        break;
      case MotionEvent.ACTION_POINTER_DOWN:
        oldDist = spacing(event);
        if (oldDist > 10f) {
          savedMatrix.set(matrix);
          midPoint(mid, event);
          mode = ZOOM;
        }
        lastEvent = new float[4];
        lastEvent[0] = event.getX(0);
        lastEvent[1] = event.getX(1);
        lastEvent[2] = event.getY(0);
        lastEvent[3] = event.getY(1);
        break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_POINTER_UP:
        mode = NONE;
        lastEvent = null;
        break;
      case MotionEvent.ACTION_MOVE:
        if (mode == ZOOM) {
          zoom(event);
        }
        break;
    }

    invalidate();
    return true;
  }

  private void zoom(MotionEvent event) {
    matrix.set(savedMatrix);

    float newDist = spacing(event);

    if (newDist > 10f) {
      matrix.set(savedMatrix);
      float scale = (newDist / oldDist);

      float[] values = new float[9];
      matrix.getValues(values);

      // limit scale matrix value
      float totalZoomedScale = scale * values[Matrix.MSCALE_X];
      if (totalZoomedScale >= MAX_ZOOM) {
        scale = MAX_ZOOM / values[Matrix.MSCALE_X];

        ViewGroup gridView = (ViewGroup) getChildAt(0);

        int childCount = gridView.getChildCount();
        for(int i = 0; i < childCount; i++) {
          if (i == 4) continue;

          if(gridView.getChildAt(i) != null && gridView.getChildAt(i).getVisibility() == View.VISIBLE) {
            View mandartItem = gridView.getChildAt(i);

            Rect rect = new Rect();
            mandartItem.getHitRect(rect);

            if (rect.contains((int) mid.x, (int) mid.y)) {
              if (i >= 4) {
                maxZoomListener.onZoomedMax(i-1);
              } else {
                maxZoomListener.onZoomedMax(i);
              }

            }
          }
        }


      } else if (totalZoomedScale <= MIN_ZOOM) {
        scale = MIN_ZOOM / values[Matrix.MSCALE_X];
      }

      matrix.postScale(scale, scale, mid.x, mid.y);
      matrix.invert(matrixInverse);
    }
  }

  private MaxZoomListener maxZoomListener;

  public void reset() {
    MAX_TRANS_X = 0f;
    MAX_TRANS_Y = 0f;
    MIN_TRANS_X = 0f;
    MIN_TRANS_Y = 0f;

    matrix = new Matrix();
    matrixInverse = new Matrix();
    savedMatrix = new Matrix();

    mode = NONE;

    start = new PointF();
    mid = new PointF();

    oldDist = 1f;
    lastEvent = null;
    mDispatchTouchEventWorkingArray = new float[2];
    mOnTouchEventWorkingArray = new float[2];

    invalidate();
  }

  public interface MaxZoomListener {
    void onZoomedMax(int position);
  }

  public void setMaxZoomListener(MaxZoomListener maxZoomListener) {
    this.maxZoomListener = maxZoomListener;
  }
}