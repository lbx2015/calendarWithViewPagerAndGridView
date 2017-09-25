# NCalendar
一款仿miui日历，月视图，周视图滑动切换，时间从1901-01-01到2099-12-31

支持农历，节假日，指示圆点，默认视图，周的第一天设置等


## 效果图

![](https://github.com/yannecer/NCalendar/blob/master/app/ncalendar3.gif)

## 下载demo：
http://fir.im/7lv4

## 使用方法

#### Gradle
```
compile 'com.necer.ncalendar:ncalendar:2.3.2'
```
##### 注意：ncalendar：1.0.x 的日历不能升级到 2.x.x，ncalendar:2.x.x是全新的日历

#### 布局文件

```
<com.necer.ncalendar.calendar.NCalendar
        android:id="@+id/ncalendar"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:defaultCalendar="Month"
        app:firstDayOfWeek="Sunday"
        app:selectCircleColor="#3388ff">

        <android.support.v7.widget.RecyclerView
            android:id="@+id/recyclerView"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

 </com.necer.ncalendar.calendar.NCalendar>
       

```


```ncalendar:2.x.x```包含一个月日历```NMonthCalendar```，一个周日历```NWeekCalendar```和一个滑动切换不同视图的```NCalendar```，
单一日历请使用```NMonthCalendar```或者```NWeekCalendar```。

```NCalendar```日历包含了周日历和月日历，通过滑动切换不同的视图，交互效果仿miui日历，尽可能的实现miui的交互逻辑。

```NCalendar```内部需要一个实现了```NestedScrollingChild```的子类，```RecyclerView```，```NestedScrollView```都可以。



### 主要Api


##### 1、监听
```
ncalendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(DateTime dateTime) {
                //日历变化回调
            }
        });
```

##### 2、跳转日期
```
参数为 yyyy-MM-dd 格式的日期

ncalendar.setDate("2017-12-31"); 
```
##### 3、回到今天
```
ncalendar.toToday(); 
```

##### 4、月-->周  周-->月
```
ncalendar.toWeek();
ncalendar.toMonth();
```
##### 5、添加指示圆点
```
List<String> list = new ArrayList<>();
list.add("2017-09-21");
list.add("2017-10-21");
list.add("2017-10-1");
list.add("2017-10-15");
list.add("2017-10-18");
list.add("2017-10-26");
list.add("2017-11-21");
ncalendar.setPoint(list);

```
##### 6、支持自定义属性，设置NCalendar默认视图、一周的第一天是周日还是周一等
```
NCalendar默认视图,Month 或者 Week，默认是 Month

app:defaultCalendar="Month"
app:defaultCalendar="Week"


设置一周开始是周一还是周日，Sunday 或者 Monday ，默认是周日Sunday

app:firstDayOfWeek="Sunday"
app:firstDayOfWeek="Monday" 

```

### 支持的属性：


| 属性| 描述|
|:---|:---|
| solarTextColor| 公历日期的文本颜色 |
| lunarTextColor| 农历日期的文本颜色 |
| solarTextSize| 公历日期的文本大小 |
| lunarTextSize| 农历日期的文本大小 |
| hintColor|不是本月的日期文本颜色 |
| selectCircleColor| 选中日期和当天的圆颜色 |
| selectCircleRadius| 选中和当天圆环半径 |
| isShowLunar| 是否显示农历 |
| hollowCircleColor| 选中空心圆中间的颜色|
| hollowCircleStroke| 选中空心圆圆环粗细 |
| calendarHeight|日历高度，在NCalendar中使用 |
| defaultCalendar|NCalendar日历默认视图|
| firstDayOfWeek|每周第一天是周日还是周一|
| duration|自动折叠时间|
| isShowHoliday|是否显示节假日|
| holidayColor|节假日“休”字颜色|
| workdayColor|工作日日“班”字颜色|
| pointSize|指示圆点大小|
| pointColor|指示圆点颜色|



### 联系我加qq ：619008099

#### View绘制：http://blog.csdn.net/y12345654321/article/details/73331253
#### 滑动处理：http://blog.csdn.net/y12345654321/article/details/77978148

