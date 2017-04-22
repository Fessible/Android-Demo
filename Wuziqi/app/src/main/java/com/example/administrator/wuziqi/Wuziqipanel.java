package com.example.administrator.wuziqi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/27.
 */

public class Wuziqipanel extends View {
    private int mPanelWidth;//棋盘宽度
    private float mLineHeight;//每个格子的高度
    public final static int MAX_LINE = 10;//格子的个数
    private Paint mPaint;//画笔
    private Bitmap mWhitePiece;//白棋
    private Bitmap mBlackPiece;//黑棋
    public final static float radioPieceofLineHeight = 3 * 1.0f / 4;//棋子图形所占的比例
    public final static int MAX_LINE_WIN = 5;

    private List<Point> mWhiteArray = new ArrayList<>();//白色棋子的坐标集
    private List<Point> mBlackArray = new ArrayList<>();//黑色棋子的坐标集
    private boolean isWhitePiece = true;//白棋先下或者轮到白棋

    private boolean isGameOver = false;//判断游戏是否结束
    private boolean isWhiteWinner;//判断是否白棋胜

    public Wuziqipanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        // setBackgroundColor(0x44ff0000);
        init();
    }

    private void init() {//初始化
        /**
         * 初始化画笔
         */
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setDither(true);// 抗抖动
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setStyle(Paint.Style.STROKE);//描边
        /**
         * 初始化棋子
         */
        mWhitePiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_w2);
        mBlackPiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_b1);

    }

    //重写onMeasure方法，进行测量

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //宽度设置为一致
        int width = Math.min(widthSize, heightSize);
        //判断widthSize和heightSize是否为空
        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }

        setMeasuredDimension(width, width);//设置尺寸
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//根据控件的尺寸来改变大小
        super.onSizeChanged(w, h, oldw, oldh);
        mPanelWidth = w;
        mLineHeight = mPanelWidth * 1.0f / MAX_LINE;
        //设置棋子的大小
        int pieceWidth = (int) (radioPieceofLineHeight * mLineHeight);
        mWhitePiece = Bitmap.createScaledBitmap(mWhitePiece, pieceWidth, pieceWidth, false);
        mBlackPiece = Bitmap.createScaledBitmap(mBlackPiece, pieceWidth, pieceWidth, false);

    }

    @Override
    protected void onDraw(Canvas canvas) {//进行绘制
        super.onDraw(canvas);
        drawBoard(canvas);//绘制棋盘
        drawPiece(canvas);//绘制棋子
        checkGameOver();
    }

    private void checkGameOver() {
        boolean whitWin = checkFiveLine(mWhiteArray);
        boolean blackWin = checkFiveLine(mBlackArray);
        if (whitWin || blackWin) {
            isGameOver = true;
            isWhiteWinner = whitWin;
            String text = isWhiteWinner ? "白棋赢" : "黑棋赢";
            showDialog(text);//显示对话框
        }
    }

    private void showDialog(String text) {//显示对话框
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("结束");
        builder.setMessage(text);
        builder.setIcon(R.drawable.wuziqi);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("再来一局", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Start();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void Start() {//重新开始
        mWhiteArray.clear();
        mBlackArray.clear();
        isGameOver = false;
        isWhiteWinner = false;
        invalidate();//重绘
    }

    private boolean checkFiveLine(List<Point> point) {
        for (Point p : point) {
            int x = p.x;
            int y = p.y;
            boolean win = checkHorizonal(x, y, point);
            if (win) {
                return true;
            }
            win = checkVertical(x, y, point);
            if (win) {
                return true;
            }
            win = checkLeft(x, y, point);
            if (win) {
                return true;
            }
            win = checkRight(x, y, point);
            if (win) {
                return true;
            }
        }
        return false;

    }

    /**
     * 判断横向棋子是否有相邻的棋子
     *
     * @param x
     * @param y
     * @param point
     * @return
     */

    private boolean checkHorizonal(int x, int y, List<Point> point) {
        int count = 1;
        //左边
        for (int i = 1; i < MAX_LINE_WIN; i++) {
            if (point.contains(new Point(x - i, y))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_LINE_WIN) {
            return true;
        }
        //右边
        for (int i = 1; i < MAX_LINE_WIN; i++) {
            if (point.contains(new Point(x + i, y))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_LINE_WIN) {
            return true;
        }

        return false;
    }

    private boolean checkVertical(int x, int y, List<Point> point) {
        int count = 1;
        //下方
        for (int i = 1; i < MAX_LINE_WIN; i++) {
            if (point.contains(new Point(x, y - i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_LINE_WIN) {
            return true;
        }
        //上边
        for (int i = 1; i < MAX_LINE_WIN; i++) {
            if (point.contains(new Point(x, y + i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_LINE_WIN) {
            return true;
        }

        return false;
    }

    private boolean checkLeft(int x, int y, List<Point> point) {
        int count = 1;
        //下方
        for (int i = 1; i < MAX_LINE_WIN; i++) {
            if (point.contains(new Point(x - i, y - i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_LINE_WIN) {
            return true;
        }
        //上边
        for (int i = 1; i < MAX_LINE_WIN; i++) {
            if (point.contains(new Point(x - i, y + i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_LINE_WIN) {
            return true;
        }

        return false;
    }

    private boolean checkRight(int x, int y, List<Point> point) {
        int count = 1;
        //下方
        for (int i = 1; i < MAX_LINE_WIN; i++) {
            if (point.contains(new Point(x + i, y - i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_LINE_WIN) {
            return true;
        }
        //上边
        for (int i = 1; i < MAX_LINE_WIN; i++) {
            if (point.contains(new Point(x + i, y + i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_LINE_WIN) {
            return true;
        }

        return false;
    }


    private void drawPiece(Canvas canvas) {//绘制棋子
        for (int i = 0; i < mWhiteArray.size(); i++) {
            Point whitePoint = mWhiteArray.get(i);
            canvas.drawBitmap(mWhitePiece,
                    (whitePoint.x + (1 - radioPieceofLineHeight) / 2) * mLineHeight,
                    (whitePoint.y + (1 - radioPieceofLineHeight) / 2) * mLineHeight, null);//棋子的坐标
        }
        for (int i = 0; i < mBlackArray.size(); i++) {
            Point blackPoint = mBlackArray.get(i);
            canvas.drawBitmap(mBlackPiece,
                    (blackPoint.x + (1 - radioPieceofLineHeight) / 2) * mLineHeight,
                    (blackPoint.y + (1 - radioPieceofLineHeight) / 2) * mLineHeight, null);//棋子的坐标
        }
    }


    private void drawBoard(Canvas canvas) {//绘制棋盘
        int w = mPanelWidth;//棋盘的宽度
        double LineHeight = mLineHeight;//每个格子的高度
        /**
         * 棋盘的开始x坐标为距离view的半个lineHeight的地方，结束的地方也距离view半个lineHeight的位置
         */

        for (int i = 0; i < MAX_LINE; i++) {
            int startX = (int) (LineHeight / 2);
            int endX = (int) (w - LineHeight / 2);
            int y = (int) ((0.5 + i) * LineHeight);
            //绘制横线
            canvas.drawLine(startX, y, endX, y, mPaint);
            //绘制竖线
            canvas.drawLine(y, startX, y, endX, mPaint);

        }
    }


    public Point getValidPoint(int x, int y) {//将坐标转换成类似(0,0)来计算
        return new Point((int) (x / mLineHeight), (int) (y / mLineHeight));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//触摸事件
        if (event.getAction() == MotionEvent.ACTION_UP) {

            if (isGameOver) {
                return false;
            }
            //获取坐标轴信息
            int x = (int) event.getX();
            int y = (int) event.getY();
            Point p = getValidPoint(x, y);

            if (mWhiteArray.contains(p) || mBlackArray.contains(p)) {//同一个位置不允许有两个棋子
                return false;
            }
            if (isWhitePiece) {
                mWhiteArray.add(p);
            } else {
                mBlackArray.add(p);
            }
            invalidate();//重新绘制
            isWhitePiece = !isWhitePiece;//白棋状态改变

        }
        return true;
    }

    public final static String INSTANCE = "instance";
    public final static String INSTANCE_GAME_OVER = "instance_game_over";
    public final static String INSTANCE_WHITEARRAY = "instance_white_array";
    public final static String INSTANCE_BLACKARRAY = "instance_blcak_array";


    //保存状态

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE,super.onSaveInstanceState());
        bundle.putBoolean(INSTANCE_GAME_OVER,isGameOver);
        bundle.putParcelableArrayList(INSTANCE_WHITEARRAY, (ArrayList<? extends Parcelable>) mWhiteArray);
        bundle.putParcelableArrayList(INSTANCE_BLACKARRAY, (ArrayList<? extends Parcelable>) mBlackArray);
        return bundle ;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        //判断state是否为Bundle子类
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;//强制转换
            isGameOver = (boolean) bundle.get(INSTANCE_GAME_OVER);
            mWhiteArray = bundle.getParcelableArrayList(INSTANCE_WHITEARRAY);
            mBlackArray = bundle.getParcelableArrayList(INSTANCE_BLACKARRAY);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE));
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
