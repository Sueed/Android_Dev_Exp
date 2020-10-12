# Exp_02 Android 界面布局

### 1. 线性布局实验——[设置为强制横屏显示]

- #### 思路

  利用LinearLayout嵌套，最外层限定垂直且权重皆为1，内层嵌套四个分别为四行限定水平，除第三行设置1:1:1:1权重外，其他行数设置1:2:1:1。

- #### 部分实验代码：

  ```xml
  <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
          xmlns:tools="http://schemas.android.com/tools"
          android:id="@+id/LinearLayout"
          android:orientation="vertical" 
          android:layout_marginBottom="10dp"
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          tools:ignore="MissingConstraints">
  
          <LinearLayout
              android:id="@+id/LinearLayout_One"
              android:layout_height="wrap_content"
              android:layout_width="match_parent"
              android:orientation="horizontal" 
              android:layout_weight="1"
              >
              <TextView
                  android:id="@+id/One_One"
                  android:layout_weight="1"
                  android:background="@drawable/border"
                      ……………………………………
  ```

- #### 实验结果如下图：

- ![avatar](https://i.ibb.co/sVMLz7Y/02-1.png)

### 2.约束布局实验——[设置为强制横屏显示]

- #### 步骤：

  ① 在values/color.xml中添加所需的颜色

  ②利用desgin视图下的可视化操作添加TextView以及对其进行约束操作

- #### 部分实验代码：

  ```xml
  <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:background="@color/colorPrimaryDark">
  
      <TextView
          android:id="@+id/constraintLayout"
          android:layout_width="100dp"
          android:layout_height="94dp"
          android:background="@color/colorRed"
          android:gravity="center"
          android:text="RED"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.027"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent"
          app:layout_constraintVertical_bias="0.051" />
  
      <TextView
          android:id="@+id/constraintLayout2"
          android:layout_width="96dp"
          android:layout_height="91dp"
          android:background="@color/colorYellow"
          android:gravity="center"
          android:text="YELLOW"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.972"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent"
          app:layout_constraintVertical_bias="0.052" />
  					…………………………
  ```

- #### 实验结果如下图：

- ![avatar](https://i.ibb.co/sC6CynH/02-2.png)

### 3.表格布局实验——[默认竖屏显示]

- #### 思路：

  ①分割线利用View显示

  ②左右对齐在TableRow中添加最左侧icon_block及两个TextView在子元素中进行gravity设置即可完成实验需求

- #### 部分实验代码：

  ```xml
  <TableLayout
          android:layout_width="350dp"
          android:layout_height="150dp"
          android:gravity="center"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent"
          tools:ignore="MissingConstraints">
  
          <TableRow
              android:id="@+id/row_one"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:layout_weight="1">
  
              <ImageView
                  android:id="@+id/icon_block_one"
                  android:layout_height="20dp"
                  android:layout_width="20dp"
                  android:layout_gravity="start"
                  />
  
              <TextView
                  android:layout_width="wrap_content"
                  android:layout_height="match_parent"
                  android:layout_weight="6"
                  android:text="Open..." />
  
              <TextView
                  android:layout_width="wrap_content"
                  android:layout_height="match_parent"
                  android:layout_weight="6"
                  android:gravity="end"
                  android:text="Ctrl+O  " />
  
          </TableRow>
  ……………………
    #分割线     
         	<View
                  android:layout_width="match_parent"
                  android:layout_height="1dp"
                  android:background="#000000"/>
  ```

- #### 实验结果如下图：

- <img src="https://i.ibb.co/YpXz64P/02-3.png" alt="avatar" style="zoom:40%;" />

### 4.补充拓展

- 为方便进行测试，本实验还利用Button响应OnClick事件通过Intent进行Acitivity之间的跳转

  【线性布局中点击Three_one处跳转至约束布局，约束布局中增设了ID为Next的Button跳转至表格布局处】

- 本实验已有打包app-debug.apk 已上传至云盘

- 链接：https://pan.baidu.com/s/1SwSITs7alglstz4Tgk3gwg 
  提取码：3kni 

