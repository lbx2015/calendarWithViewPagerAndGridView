package com.necer.ncalendar.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.Toast;

import com.necer.ncalendar.R;
import com.necer.ncalendar.adapter.NCalendarGridAdapter;
import com.necer.ncalendar.adapter.NMonthGridAdapter;
import com.necer.ncalendar.listener.OnClickMonthViewListener;
import com.necer.ncalendar.listener.OnMonthCalendarChangedListener;
import com.necer.ncalendar.utils.Utils;
import com.necer.ncalendar.view.NCalendarGridView;
import com.necer.ncalendar.view.NMonthGridView;

import org.joda.time.DateTime;
import org.joda.time.Months;

/**
 * Created by 闫彬彬 on 2017/8/28.
 * QQ:619008099
 */

public class NMonthGridCalendar extends NCalendarGridPager implements OnClickMonthViewListener {

    private OnMonthCalendarChangedListener onMonthCalendarChangedListener;
    private int lastPosition = -1;

    public NMonthGridCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected NMonthGridAdapter getCalendarAdapter() {
        mPageSize = Months.monthsBetween(startDateTime, endDateTime).getMonths() + 1;
        mCurrPage = Months.monthsBetween(startDateTime, DateTime.now()).getMonths();
        return new NMonthGridAdapter(getContext(), mPageSize, mCurrPage, mInitialDateTime, this);
    }

    @Override
    protected void initCurrentCalendarView(int position) {

        NMonthGridView currView = (NMonthGridView) calendarAdapter.getCalendarViews().get(position);
        NMonthGridView lastView = (NMonthGridView) calendarAdapter.getCalendarViews().get(position - 1);
        NMonthGridView nextView = (NMonthGridView) calendarAdapter.getCalendarViews().get(position + 1);

        if (lastView != null)
            lastView.clear();

        if (nextView != null)
            nextView.clear();

        if (lastPosition == -1) {
            lastPosition = position;
            currView.setDateTimeAndPoint(mInitialDateTime, pointList);
            mSelectDateTime = mInitialDateTime;
        } else if (!isSetDateTime) {
            int i = position - lastPosition;
            DateTime dateTime = mSelectDateTime.plusMonths(i);

            //日期越界
            if (dateTime.getYear() > endDateTime.getYear()) {
                dateTime = endDateTime;
            } else if (dateTime.getYear() < startDateTime.getYear()) {
                dateTime = startDateTime;
            }

            //currView.setSelectDateTime(dateTime);
            currView.setDateTimeAndPoint(dateTime, pointList);

            mSelectDateTime = dateTime;
        }
        lastPosition = position;

        //正常滑动处理
        if (onMonthCalendarChangedListener != null && !isSetDateTime) {
            onMonthCalendarChangedListener.onMonthCalendarChanged(mSelectDateTime);
        }

    }

    public void setOnMonthCalendarChangedListener(OnMonthCalendarChangedListener onMonthCalendarChangedListener) {
        this.onMonthCalendarChangedListener = onMonthCalendarChangedListener;
    }

    @Override
    protected void setDateTime(DateTime dateTime) {

        if (dateTime.getYear() > endDateTime.getYear() || dateTime.getYear() < startDateTime.getYear()) {
            Toast.makeText(getContext(), R.string.illegal_date, Toast.LENGTH_SHORT).show();
            return;
        }

        SparseArray<NCalendarGridView> calendarViews = calendarAdapter.getCalendarViews();
        if (calendarViews.size() == 0) {
            return;
        }

        isSetDateTime = true;

        DateTime initialDateTime = calendarViews.get(getCurrentItem()).getInitialDateTime();
        int months = Utils.getIntervalMonths(initialDateTime, dateTime);
        int i = getCurrentItem() + months;
        setCurrentItem(i, false);

        NMonthGridView monthView = (NMonthGridView) calendarAdapter.getCalendarViews().get(i);
        if (monthView == null) {
            return;
        }

        monthView.setDateTimeAndPoint(dateTime, pointList);
        mSelectDateTime = dateTime;

        isSetDateTime = false;

        //跳转处理
        if (onMonthCalendarChangedListener != null) {
            onMonthCalendarChangedListener.onMonthCalendarChanged(mSelectDateTime);
        }

    }

    @Override
    public void onClickCurrentMonth(DateTime dateTime) {
        dealClickEvent(dateTime, getCurrentItem());
    }

    @Override
    public void onClickLastMonth(DateTime dateTime) {
        int currentItem = getCurrentItem() - 1;
        dealClickEvent(dateTime, currentItem);
    }

    @Override
    public void onClickNextMonth(DateTime dateTime) {
        int currentItem = getCurrentItem() + 1;
        dealClickEvent(dateTime, currentItem);
    }

    private void dealClickEvent(DateTime dateTime, int currentItem) {

        if (dateTime.getYear() > endDateTime.getYear() || dateTime.getYear() < startDateTime.getYear()) {
            Toast.makeText(getContext(), R.string.illegal_date, Toast.LENGTH_SHORT).show();
            return;
        }

        NMonthGridCalendar.this.setCurrentItem(currentItem);
        NMonthGridView nMonthView = (NMonthGridView) calendarAdapter.getCalendarViews().get(currentItem);
        if (nMonthView == null) {
            return;
        }
        nMonthView.setDateTimeAndPoint(dateTime, pointList);
        mSelectDateTime = dateTime;

        if (onMonthCalendarChangedListener != null) {
            onMonthCalendarChangedListener.onMonthCalendarChanged(dateTime);
        }


    }

    public NMonthGridView getCurrectMonthView() {
        return (NMonthGridView) calendarAdapter.getCalendarViews().get(getCurrentItem());
    }

}
