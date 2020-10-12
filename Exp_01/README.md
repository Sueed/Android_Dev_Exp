## Exp_01 Android开发基础

- #### 安装Android Studio【官网下载】

- #### 创建Android工程同步至Github

  - ##### 利用Git Bash命令行进行同步

    - 在目标文件夹下执行git init创建本地仓库【若已创建过，再次git init会初始化仓库】

    - vim .gitignore 编辑忽略文件

      ![avatar](https://i.ibb.co/Y3RZkX0/01-1.png)

    - 在本地仓库可手动或使用touch命令创建新文件、mkdir创建新文件夹

      在添加（或更新、删除某些文件）后git status 查看仓库情况

      ![avatar](https://i.ibb.co/VgSNpPT/01-2.png)

      根据提示对各个有变动的文件批量（git commit -a 、git add -u）或单独进行commit，add/rm  \<file> 处理至git status时显示working tree clean后，即可使用git push更新至git仓库，如下图：

      ![avatar](https://i.ibb.co/wLvFHLs/01-3.png)

    

  - ##### 利用Git Gui 图形界面进行同步操作【仅做了解，主要还是使用Git Bash操作】

    - https://juejin.im/post/6844904101608701959

