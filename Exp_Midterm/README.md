# Exp_Midterm Android NotePad笔记本应用

## ①基础功能：

### 1. NoteList中显示条目增加时间戳显示

- #### 思路

  ①note_layout中设置TextView以显示取到的时间

  ②在编辑类中新增时间获取方法dateToStr()，当判断编辑与旧数据有变动时进行数据更新

- #### 部分实验代码：

  note_layout.xml

  ```xml
  <TextView
          android:id="@+id/tv_time"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="Time"
          android:textSize="16dp"
          android:textColor="@color/greyC"/>
  ```

  EditActivity.java

  ```java
  public String dateToStr(){
          Date date = new Date();
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          return simpleDateFormat.format(date);
      }
  ```

  #### 实验截图：
  
  <img src="https://i.ibb.co/Ws0Y3Hg/Midterm-01.png" width:'250px' /><img src="https://i.ibb.co/THdKxkq/Midterm-02.png" width:'250px' />


### 2.添加笔记查询功能（根据标题查询）

- #### 思路：

  ①界面上增加搜索icon的item和点击后触发输入关键词的textview接收关键词信息

  ②定义关键词过滤规则存储过滤后的笔记信息并刷新界面

- #### 部分实验代码：

  main_menu.xml

  ```xml
  <item
          android:id="@+id/action_search"
          android:icon="?attr/menu_search"
          app:showAsAction="always"
          app:actionViewClass="androidx.appcompat.widget.SearchView"
          android:title="Search"
          />
  ```

  NoteAdapter.java

  ```java
  class MyFilter extends Filter {
          //performFiltering(CharSequence charSequence)中定义过滤规则
          @Override
          protected FilterResults performFiltering(CharSequence charSequence) {
              FilterResults result = new FilterResults();
              List<Note> list;
              if (TextUtils.isEmpty(charSequence)) {//当过滤的关键字为空的时候，显示所有的数据
                  list = backList;
              } else {//否则把符合条件的数据对象添加到集合中
                  list = new ArrayList<>();
                  for (Note note : backList) {
                      if (note.getContent().contains(charSequence)) {
                          list.add(note);
                      }
  
                  }
              }
              result.values = list; //将得到的集合保存到FilterResults的value变量中
              result.count = list.size();//将集合的大小保存到FilterResults的count变量中
  
              return result;
          }
          //在publishResults方法中告诉适配器更新界面
          @Override
          protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
              noteList = (List<Note>)filterResults.values;
              if (filterResults.count>0){
                  notifyDataSetChanged();//通知数据发生了改变
              }else {
                  notifyDataSetInvalidated();//通知数据失效
              }
          }
      }
  ```

  #### 实验截图：
  
  <img src="https://i.ibb.co/LQ3b82h/Midterm-03.png" alt="avatar" style="width:250px" /><img src="https://i.ibb.co/z7Hs3vZ/Midterm-04.png" alt="avatar" style="width:250px" />

## ②附加功能：

### 1. UI美化、FloatingButton样式选择、白天黑夜模式切换

* #### 思路

  ①预先编写主题改变后的样式效果

  ②通过监听onclick等方法触发对应改变的样式覆盖旧样式

* #### 部分实验代码：

  MainActivity.java

  ```java
  //修改FloatingButton样式
  private void chooseFabColor(int fabColor) {
  
          switch (fabColor) {
              case -500072:
                  fab.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.q)));
                  break;
              case -500081:
                  fab.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.w)));
                  break;
              case...
  ```

  ```java
  //黑夜模式
  private void setSelfNightMode(){
          //重新赋值并重启本activity
  
          super.setNightMode();
          Intent intent = new Intent(this, UserSettingsActivity.class);
          intent.putExtra("night_change", !night_change); //重启一次，正负颠倒。最终为正值时重启MainActivity。
  
          startActivity(new Intent(this, UserSettingsActivity.class));
          overridePendingTransition(R.anim.night_switch, R.anim.night_switch_over);
          finish();
      }
  
      private void setNightModePref(boolean night){
          //通过nightMode switch修改pref中的nightMode
          sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
          SharedPreferences.Editor editor = sharedPreferences.edit();
          editor.putBoolean("nightMode", night);
          editor.commit();
      }
  ```

  #### 实验截图：
  
  <img src="https://i.ibb.co/Cv9J3xx/Midterm-05.png" alt="avatar" style="width:250px" /><img src="https://i.ibb.co/6ZtKBLY/Midterm-06.png" alt="avatar" style="width:250px" />
  
  <img src="https://i.ibb.co/ZLnhz9x/Midterm-07.png" alt="avatar" style="width:250px" /><img src="https://i.ibb.co/nkWM9NZ/Midterm-08.png" alt="avatar" style="width:250px" />


### 2.  按时间正序或倒序排列笔记/计划，长按删除

* #### 思路

  ①编写Reverse及Longclick相关方法对数据库中已有的笔记项进行相应操作

  ②监听触发方法【长按先显示判断toast以免误触】，操作完成后结果通知界面刷新展示

* #### 部分实验代码：

  MainActivity.java

  ```java
  //按模式时间排序笔记
      public void sortNotes(List<Note> noteList, final int mode) {
          Collections.sort(noteList, new Comparator<Note>() {
              @Override
              public int compare(Note o1, Note o2) {
                  try {
                      if (mode == 1) {
                          Log.d(TAG, "sortnotes 1");
                          return npLong(dateStrToSec(o2.getTime()) - dateStrToSec(o1.getTime()));
                      }
                      else if (mode == 2) {//reverseSort
                          Log.d(TAG, "sortnotes 2");
                          return npLong(dateStrToSec(o1.getTime()) - dateStrToSec(o2.getTime()));
                      }
  //按模式时间排序计划
  //类似代码不做展示
  ```

  ```java
  //长按删除
  public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
          switch (parent.getId()){
              case R.id.lv:
                  final Note note = noteList.get(position);
                  new AlertDialog.Builder(MainActivity.this)
                          .setMessage("Do you want to delete this note ?")
                          .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  CRUD op = new CRUD(context);
                                  op.open();
                                  op.removeNote(note);
                                  op.close();
                                  refreshListView();
                              }
  ```

  #### 实验截图：
  
  <img src="https://i.ibb.co/2nwnk1n/Midterm-09.png" alt="avatar" style="width:250px" /><img src="https://i.ibb.co/5X3CBsw/Midterm-10.png" alt="avatar" style="width:250px" />
  
  <img src="https://i.ibb.co/fMG9L7z/Midterm-11.png" alt="avatar" style="width:250px" /><img src="https://i.ibb.co/BTVbmCT/Midterm-12.png" alt="avatar" style="width:250px" />


### 3. 笔记标签分类，可自定义新标签，按标签分类统计/查询

* #### 思路

  ①新增数据项属性标签，可自定义标签名称【提供默认标签和无标签状态且总标签数设置上限】

  ②若监听到触发根据标签分类查询的方法按该标签对象对已有数据项进行筛选刷新展示

* #### 部分实验代码：

  MainAcitivty.java

  ```java
  //统计不同标签的笔记数
      public List<Integer> numOfTagNotes(List<String> noteStringList){
          Integer[] numbers = new Integer[noteStringList.size()];
          for(int i = 0; i < numbers.length; i++) numbers[i] = 0;
          for(int i = 0; i < noteList.size(); i++){
              numbers[noteList.get(i).getTag() - 1] ++;
          }
          return Arrays.asList(numbers);
      }
  //新增自定义标签
  add_tag.setOnClickListener(new OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          if (sharedPreferences.getString("tagListString","").split("_").length < 8) {
                              final EditText et = new EditText(context);
                              new AlertDialog.Builder(MainActivity.this)
                                      .setMessage("Enter the name of tag")
                                      .setView(et)
                                  ......
  }
          else Toast.makeText(context, "Repeated tag!", Toast.LENGTH_SHORT).show();
  	}                           ......
  else{
       Toast.makeText(context, "自定义的标签够多了！", Toast.LENGTH_SHORT).show();
      }
  //按选中标签分类展示
  List<String> tagList = Arrays.asList(sharedPreferences.getString("tagListString", null).split("_")); //获取tags
                  tagAdapter = new TagAdapter(context, tagList, numOfTagNotes(tagList));
                  lv_tag.setAdapter(tagAdapter);
  
                  lv_tag.setOnItemClickListener(new OnItemClickListener() {
                      @Override
                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                          List<String> tagList = Arrays.asList
                              	......
  ```

  #### 实验截图：
  
  <img src="https://i.ibb.co/KhfYGfm/Midterm-13.png" alt="avatar" style="width:250px" /><img src="https://i.ibb.co/C9qbcbH/Midterm-14.png" alt="avatar" style="width:250px" />
  
  <img src="https://i.ibb.co/0CcCykp/Midterm-15.png" alt="avatar" style="width:250px" /><img src="https://i.ibb.co/pxJRSpf/Midterm-16.png" alt="avatar" style="width:250px" />


### 4. 新增计划类型plan，设置定时触发Alarm提醒

* #### 思路

  ①基础功能类似于笔记，额外做plandatabase存储信息和可设置alarm，利用togglebar与笔记做分别展示

  ②编写alarm相关edit、receiver、adapter等类方法，创建

* #### 部分实验代码：

  edit_alarm_layout.xml

  ```xml
  <TextView
                      style="@style/centerTV"
                      android:id="@+id/date/time"
                      android:layout_width="0dp"
                      android:layout_height="match_parent"
                      android:layout_weight="1"
                      android:textSize="18dp"
                      android:textColor="?attr/tvMainColor"/>
                  <Button
                      android:id="@+id/set_date/time"
                      android:layout_width="wrap_content"
                      android:layout_height="match_parent"
                      android:text="Set Date/Time"
                      />
  ```

  alarm_receiver.java

  ```java
  public class AlarmReceiver extends BroadcastReceiver {
  
      private String channelId = "Exp_Midterm";
      private String name = "ChannelName";
      @Override
      public void onReceive(Context context, Intent intent) {
          String title = intent.getExtras().getString("title");
          String content = intent.getExtras().getString("content");
          int id = intent.getExtras().getInt("id");
          Intent intent1 = new Intent(context, MainActivity.class);
  
          PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent1, 0);
          intent1.putExtra("mode", 1);
          NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
              NotificationChannel mChannel = new NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_DEFAULT);
              mChannel.enableVibration(true);
              manager.createNotificationChannel(mChannel);
          }
  								......
  ```

  MainAcitivity.java

  ```java
  //设置提醒
      private void startAlarm(Plan p) {
          Calendar c = p.getPlanTime();
          if(!c.before(Calendar.getInstance())) {
              Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
              intent.putExtra("title", p.getTitle());
              intent.putExtra("content", p.getContent());
              intent.putExtra("id", (int)p.getId());
              PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int) p.getId(), intent, 0);
  
              alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
          }
      }
  
      //设置很多提醒
      private void startAlarms(List<Plan> plans){
          for(int i = 0; i < plans.size(); i++) startAlarm(plans.get(i));
      }
  
      //取消提醒
      private void cancelAlarm(Plan p) {
          Intent intent = new Intent(this, AlarmReceiver.class);
          PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int)p.getId(), intent, 0);
          alarmManager.cancel(pendingIntent);
      }
  
      //取消很多提醒
      private void cancelAlarms(List<Plan> plans){
          for(int i = 0; i < plans.size(); i++) cancelAlarm(plans.get(i));
      }
  ```

  #### 实验截图：
  
  <img src="https://i.ibb.co/kG3JF3M/Midterm-17.png" alt="avatar" style="width:250px" /><img src="https://i.ibb.co/NtdZy7q/Midterm-18.png" alt="avatar" style="width:250px" />
  
  <img src="https://i.ibb.co/zxXJyRx/Midterm-19.png" alt="avatar" style="width:250px" /><img src="https://i.ibb.co/NWXYSG0/Midterm-20.png" alt="avatar" style="width:250px" />
  
  <img src="https://i.ibb.co/DfrPK1m/Midterm-21.png" alt="avatar" style="width:250px" /><img src="https://i.ibb.co/fvBTqNC/Midterm-22.png" alt="avatar" style="width:250px" />
  
  <img src="https://i.ibb.co/PDRfz4f/Midterm-23.png" alt="avatar" style="width:250px" /><img src="https://i.ibb.co/Rc9bMht/Midterm-24.png" alt="avatar" style="width:250px" />
