package com.necer.ncalendar.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.GridView;

import com.necer.ncalendar.utils.Attrs;
import com.necer.ncalendar.utils.Utils;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 闫彬彬 on 2017/8/29.
 * QQ:619008099
 */

public abstract class NCalendarGridView extends GridView {

    public DateTime mInitialDateTime;//初始传入的datetime，
    public DateTime mSelectDateTime;//被选中的datetime
    public int mWidth;
    public int mHeight;
    public List<DateTime> dateTimes;

    public int mSolarTextColor;//公历字体颜色
    public int mLunarTextColor;//农历字体颜色
    public int mHintColor;//不是当月的颜色
    public float mSolarTextSize;
    public float mLunarTextSize;
    public Paint mSorlarPaint;
    public Paint mLunarPaint;
    public int mSelectCircleRadius;//选中圆的半径
    public int mSelectCircleColor;//选中圆的颜色
    public boolean isShowLunar;//是否显示农历

    public int mHolidayColor;
    public int mWorkdayColor;

    public List<Rect> mRectList;//点击用的矩形集合
    public int mPointColor;//圆点颜色
    public float mPointSize;//圆点大小

    public int mHollowCircleColor;//空心圆颜色
    public int mHollowCircleStroke;//空心圆粗细

    public boolean isShowHoliday;//是否显示节假日
    public List<String> holidayList;
    public List<String> workdayList;
    public List<String> pointList;


    public NCalendarGridView(Context context) {
        super(context);
        init();
    }

    public void init() {
        mSolarTextColor = Attrs.solarTextColor;
        mLunarTextColor = Attrs.lunarTextColor;
        mHintColor = Attrs.hintColor;
        mSolarTextSize = Attrs.solarTextSize;
        mLunarTextSize = Attrs.lunarTextSize;
        mSelectCircleRadius = Attrs.selectCircleRadius;
        mSelectCircleColor = Attrs.selectCircleColor;
        isShowLunar = Attrs.isShowLunar;

        mPointSize = Attrs.pointSize;
        mPointColor = Attrs.pointColor;
        mHollowCircleColor = Attrs.hollowCircleColor;
        mHollowCircleStroke = Attrs.hollowCircleStroke;

        isShowHoliday = Attrs.isShowHoliday;
        mHolidayColor = Attrs.holidayColor;
        mWorkdayColor = Attrs.workdayColor;

        mRectList = new ArrayList<>();
        mSorlarPaint = getPaint(mSolarTextColor, mSolarTextSize);
        mLunarPaint = getPaint(mLunarTextColor, mLunarTextSize);

        holidayList = Utils.getHolidayList(getContext());
        workdayList = Utils.getWorkdayList(getContext());
    }


    private Paint getPaint(int paintColor, float paintSize) {
        Paint paint = new Paint();
        paint.setColor(paintColor);
        paint.setTextSize(paintSize);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }

    public DateTime getInitialDateTime() {
        return mInitialDateTime;
    }

    public DateTime getSelectDateTime() {
        return mSelectDateTime;
    }

    public void setSelectDateTime(DateTime dateTime) {
        this.mSelectDateTime = dateTime;
        invalidate();
    }

    public void setDateTimeAndPoint(DateTime dateTime, List<String> pointList) {
        this.mSelectDateTime = dateTime;
        this.pointList = pointList;
        invalidate();
    }

    public void clear() {
        this.mSelectDateTime = null;
        invalidate();
    }

    public void setPointList(List<String> pointList) {
        this.pointList = pointList;
        invalidate();
    }
}
