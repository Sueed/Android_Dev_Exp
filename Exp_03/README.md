# Exp_03 Android UI组件

### 1. SimpleAdapter、ListView

- #### 思路

  ①layout文件中编写单元组件，在MainActivity中利用SimpleAdapter绑定组件进行for循环遍历输出

  ②Toast显示，响应选中事件的列表项取其对应的name属性值

- #### ①部分实验代码：

  ```java
  view_select = new HashMap<>();
  
          //loading name list
          List<Map<String, Object>> listItems = new ArrayList<>();
          for (int i = 0; i < names.length; i++) {
              Map<String, Object> listItem = new HashMap<>();
              listItem.put("Name", names[i]);
              listItem.put("Img", imgs[i]);
              listItems.add(listItem);
          }
  
          //create a SimpleAdapter
          SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.list_unit_01, new String[]{"Name", "Img"}, new int[]{R.id.list_unit_01_name, R.id.list_unit_01_img});
          ListView list = findViewById(R.id.LV_demo);
  
          //set Adapter for ListView
          list.setAdapter(simpleAdapter);
  		……………………………………
  ```

  ```xml
  <! -- LIST_UNIT_01 -->
  <LinearLayout
          android:id="@+id/list_unit_01"
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          android:orientation="horizontal"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent"
          android:onClick="click_select">
  
          <TextView
              android:id="@+id/list_unit_01_name"
              android:layout_width="0dp"
              android:layout_height="match_parent"
              android:layout_gravity="fill"
              android:layout_weight="1"
              android:layout_marginLeft="10dp"
              android:gravity="center_vertical|start"
              android:textSize="18sp" />
  
          <ImageView
              android:id="@+id/list_unit_01_img"
  ```

- #### ②部分实验代码：

  ```java
      //click_to_select block
      public void click_select (View V) throws Exception{
          LinearLayout list_unit = V.findViewById(R.id.list_unit_01);
          TextView name = V.findViewById(R.id.list_unit_01_name);
              if (view_select.get(V) == null || !view_select.get(V)) {
                  list_unit.setBackgroundColor(Color.BLUE);
                  view_select.put(V, true);
                  Toast toast = Toast.makeText(MainActivity.this, name.getText(), Toast.LENGTH_SHORT);
                  toast.show();
              } else {
                  list_unit.setBackgroundColor(Color.WHITE);
                  view_select.put(V, false);
                  AlertDialog.Builder builder = new AlertDialog.Builder(this);
                  builder.setView(View.inflate(this, R.layout.alert, null));
                  builder.show();
              }
      }
  ```

- #### 实验结果如下图：

- <img src="https://i.ibb.co/8NDBmvs/03-1.png" alt="avatar" style="zoom:20%;" />

### 2.自定义布局的AlertDialog

- #### 思路：

  ①layout文件中编写alert样式 

  ②通过AlertDialog.builder设置builder.setView

- #### ①部分实验代码：

  ```xml
  <TableLayout
          android:id="@+id/alert"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent">
  
          <TableRow
              android:layout_width="match_parent"
              android:layout_height="match_parent">
  
              <TextView
                  android:id="@+id/alert_title"
                  android:layout_width="match_parent"
                  android:layout_height="80dp"
                  android:layout_weight="1"
                  android:background="#FFE600"
                  android:fontFamily="cursive"
                  android:gravity="center"
                  android:textColor="@color/colorPrimary"
                  android:text="ANDROID APP"
                  android:textSize="24sp" />
          </TableRow>
  
          <TableRow
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:gravity="center">
  
              <EditText
                  android:id="@+id/alert_username"
  					…………………………
  ```
  
- #### ②部分实验代码：

  ```java
     AlertDialog.Builder builder = new AlertDialog.Builder(this);
     builder.setView(View.inflate(this, R.layout.alert, null));
     builder.show();
  ```

- #### 实验结果如下图：

- <img src="https://i.ibb.co/52SFCfj/03-2.png" alt="avatar" style="zoom:50%; width:750px" />

### 3.使用XML定义菜单

- #### 思路：

  ①创建menu_demo.xml编写菜单样式

  ②在MenuActivity中装填对应的菜单并添加到menu中

- #### ①部分实验代码：

  ```xml
  <menu>
      <item android:title="字体大小">
          <menu >
              <item
                  android:id="@+id/font_small"
                  android:title="小" />
              <item
                  android:id="@+id/font_mid"
                  android:title="中" />
              <item
                  android:id="@+id/mi_big"
                  android:title="大" />
          </menu>
      </item>
  </menu>
  ```
  
- #### ②部分实验代码：

  ```java
  public boolean onCreateOptionsMenu (Menu menu){
      getMenuInflater().inflate(R.menu.menu_demo, menu);
      return super.onCrateOptionsMenu(menu);
  }
  ```

- #### 实验结果如下图：

- <img src="https://i.ibb.co/71cBQmt/03-3.png" alt="avatar" style="zoom:50%; width:750px" />

### 4.ActionMode的上下文菜单

- #### 思路：

  ①创建菜单样式menu_blank

  ②ActionModeActivity中注册View，实现ActionMode.callback回调

- #### 部分代码：

  ```java
  public void onCreateContextMenu(ContextMenu menu, View v,
                                  ContextMenuInfo menuInfo) {
      super.onCreateContextMenu(menu, v, menuInfo);
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.menu_blank, menu);
  }
  
  ……………………
      
  ActionMode.Callback callback = new ActionMode.Callback()
      {
  
          @Override
          public boolean onCreateActionMode(ActionMode actionMode, Menu menu)
          {
              getMenuInflater().inflate(R.menu.menu_blank, menu);
              return true;
          }
  
          …………
  
          @Override
          public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem)
          {
              actionMode.setTitle(selected_items + " selected");
              return true;
          }
      	
      	…………
      	
      };
  ```

- #### 实验结果如下图：

- <img src="https://i.ibb.co/5vkCL6H/03-4.png" alt="avatar" style="zoom:50%; width:750px" />

### 5.补充拓展

- 为方便进行测试，本实验还利用OnClick事件响应通过Intent进行Acitivity之间的跳转

- 本实验已有打包Exp_03.apk 已上传至云盘
- 链接：https://pan.baidu.com/s/17LbJ_3lf8NZbH8gEpNQseA 
  提取码：3ujx 

