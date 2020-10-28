# Exp_04 Android Intent

### 1. 自定义WebView验证隐式Intent的使用

- #### 思路

  ①Exp_04_2中自定义WebView用于接收隐式intent加载URL

  ②Exp_04_1中设置EditText用于输入URL，Button按钮setonclicklistenter监听点击事件触发隐式intent

- #### Exp_04_1部分实验代码：

  activity_main.xml

  ```xml
  <EditText
              android:id="@+id/URL_content"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:hint="please input URL" />
  
  ……………………
  
  <Button
          android:id="@+id/browse_button"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="浏览该网页"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintHorizontal_bias="0.498"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toBottomOf="@+id/linearLayout"
          app:layout_constraintVertical_bias="0.0"
          tools:ignore="NotSibling" />
  ```

- MainActivity.java

  ```java
  url = findViewById(R.id.URL_content);
          Button browse_bt = findViewById(R.id.browse_button);
          browse_bt.setOnClickListener(new View.OnClickListener() {
              public void onClick(View v) {
                  // Code here executes on main thread after user presses button
                  Intent intent = new Intent();
                  intent.setAction(Intent.ACTION_VIEW);
  
                  String link = url.getText().toString();
                  if (!link.startsWith("https://"))
                  {
                      link = "https://" + link;
                  }
                  Uri uri = Uri.parse(link);
                  intent.setData(uri);
                  startActivity(intent);
              }
          });
  ```

- #### Exp_04_2部分实验代码：

  activity_main.xml

  ```xml
  <WebView
          android:id="@+id/webView"
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          app:layout_constraintBottom_toBottomOf="parent"
          app:layout_constraintEnd_toEndOf="parent"
          app:layout_constraintStart_toStartOf="parent"
          app:layout_constraintTop_toTopOf="parent" />
  ```

  MainActivity.java

  ```java
  webView = findViewById(R.id.webView);
          webView.setWebViewClient(new WebViewClient());
          webView.setWebChromeClient(new WebChromeClient());
      }
  
      @Override
      public void onWindowFocusChanged(boolean hasFocus)
      {
          super.onWindowFocusChanged(hasFocus);
          webView.loadUrl(Objects.requireNonNull(getIntent().getData()).toString());
      }
  ```

  AndroidMainifest.xml

  ```xml
  			<intent-filter>
                  <action android:name="android.intent.action.VIEW" />
  
                  <category android:name="android.intent.category.DEFAULT" />
                  <category android:name="android.intent.category.BROWSABLE" />
  
                  <data android:scheme="http" />
                  <data android:scheme="https" />
              </intent-filter>
  
  			………………
  
  			<uses-permission android:name="android.permission.INTERNET" />
  ```

- #### 实验结果如下图：

- <img src="https://i.ibb.co/8dsLX5w/04-1.png" alt="avatar" style="zoom:50%;" />

- <img src="https://i.ibb.co/8z9YMFW/04-2.png" alt="avatar" style="zoom:50%;" />

### 2.参考资料

- ERR_CACHE_MISS 报错解决：https://blog.csdn.net/msn465780/article/details/68061033

